package com.ld.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.ld.bean.Cart;
import com.ld.bean.CartItem;
import com.ld.bean.Order;
import com.ld.bean.OrderItem;
import com.ld.bean.PageBean;
import com.ld.bean.Product;
import com.ld.bean.User;
import com.ld.service.ProductService;
import com.ld.utils.AlipayConfig;
import com.ld.utils.CommonsUtils;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("hostProduct", productService.findHotProduct(1, 9));
		model.addAttribute("newProduct", productService.findNewProduct(1, 9));
		return "index";
	}
	@RequestMapping("/product")//访问商品列表
	public String product(HttpServletRequest request) {
		String cid = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr==null) currentPageStr = "1";
		int currentPage = Integer.parseInt(currentPageStr);
		int currentCount = 12;//当前页最大数
		
		PageBean<Product> pageBean = productService.findProductByCid(cid,currentPage,currentCount);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		
		//定义一个记录历史商品信息的集合
		List<Product> historyProductList = new ArrayList<Product>();
		
		//获得客户端携带名字叫pids的cookie
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				if("pids".equals(cookie.getName())) {
					String pids = cookie.getValue();
					String[] split = pids.split("-");
					for(String pid:split){
						Product pro = productService.findProductByPid(pid);
						historyProductList.add(pro);
					}
				}
			}
		}
		//将历史记录的集合放到域中
		request.setAttribute("historyProductList", historyProductList);
		
		return "product_list";
	}
	@RequestMapping("/productInfo")//访问商品
	public String productInfo(HttpServletRequest request,Model model,HttpServletResponse response) {
		String pid = request.getParameter("pid");
		String cid = request.getParameter("cid");
		String currentPage = request.getParameter("currentPage");
		model.addAttribute("product", productService.findProductByPid(pid));
		model.addAttribute("cid", cid);
		model.addAttribute("currentPage",currentPage);
		
		//获得客户端携带cookie---获得名字是pids的cookie
		String pids = pid;
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie : cookies){
				if("pids".equals(cookie.getName())) {
					pids = cookie.getValue();
					//将pids拆成一个数组
					String[] split = pids.split("-");
					List<String> aslist = Arrays.asList(split);
					LinkedList<String> list = new LinkedList<String>(aslist);
					//判断集合中是否存在当前pid
					if(list.contains(pid)){
						//包含当前的pid
						list.remove(pid);
					}
					list.addFirst(pid);
					//将数转成字符串
					StringBuffer sb = new StringBuffer();
					for(int i=0;i<list.size()&&i<7;i++){
						sb.append(list.get(i));
						sb.append("-");
					}
					//去掉最后的"-"
					pids = sb.substring(0, sb.length()-1);
				}
			}
		}
		Cookie cookie1 = new Cookie("pids", pids);
		response.addCookie(cookie1);
		return "product_info";
	}
	//添加商品到购物车
	@RequestMapping("/addProductToCart")
	public String addProductToCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		//获得要放到购物车的商品的pid
		String pid = request.getParameter("pid");
		//获得该商品的购买数量
		int buyNum = Integer.parseInt(request.getParameter("buyNum"));
		//获得product对象
		Product product = productService.findProductByPid(pid);
		//计算小计
		double subtotal = product.getShop_price()*buyNum;
		//封装cartItem
		CartItem item = new CartItem();
		item.setProduct(product);
		item.setBuyNum(buyNum);
		item.setSubtotal(subtotal);
		//获得购物车---判断是否在session已经存在购物车
		Cart cart = (Cart) session.getAttribute("cart");
		double newsubtotal = 0.0;
		
		if(cart==null) {
			cart = new Cart();
		}
		//将购物项放入车中
		//先判断购物车中是否已包含购物项---判断key是否存在
		//如果购物车已存在该商品----将现在买的数量与原来的数量进行相加操作
		Map<String,CartItem> cartItems = cart.getCartItems();
		if(cartItems.containsKey(pid)) {
			//取出商品的数量
			CartItem cartItem = cartItems.get(pid);
			int oldBuyNum = cartItem.getBuyNum();
			oldBuyNum += buyNum;
			cartItem.setBuyNum(oldBuyNum);
			cart.setCartItems(cartItems);
			//修改小计
			 double oldsubtotal = cartItem.getSubtotal();
			 newsubtotal = buyNum*product.getShop_price();
			 cartItem.setSubtotal(oldsubtotal+newsubtotal);
			
		}else {
			//如果车中没有
			cart.getCartItems().put(product.getPid(), item);
			newsubtotal = buyNum*product.getShop_price();
		}
		
		//计算总计
		 double total = cart.getTotal()+newsubtotal;
		 cart.setTotal(total);
		
		//将车再次访问session
		session.setAttribute("cart", cart);
		//直接跳转到购物车页面
		
		return "redirect:/cart";
	}
	//删除单一商品
	@RequestMapping("/delProFromCart")
	public String delProFromCart(HttpServletRequest request) {
		String pid = request.getParameter("pid");
		//删除session购物车中的购物项集合中的item
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart!=null) {
			Map<String, CartItem> cartItems = cart.getCartItems();
			//需要修改总价
			cart.setTotal(cart.getTotal()-cartItems.get(pid).getSubtotal());
			//删除
			cartItems.remove(pid);
		}
		session.setAttribute("cart", cart);
		return "redirect:/cart";
	}
	//清空购物车
	@RequestMapping("/clearCart")
	public String clearCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("cart");
		return "redirect:/cart";
	}
	//提交订单
	@RequestMapping("/submitOrder")
	public String submitOrder(HttpServletRequest request) {
		HttpSession session = request.getSession();
		//判断用户是否已经登录
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login";
		}
		//封装Order
		Order order = new Order();
		String oid = CommonsUtils.getUUID();
		order.setOid(oid);
		order.setOrdertime(new Date());
		//获得session中的购物车
		Cart cart = (Cart) session.getAttribute("cart");
		double total = cart.getTotal();
		order.setTotal(total);
		order.setState(0);
		order.setAddress(null);
		order.setName(null);
		order.setTelephone(null);
		order.setUser(user);
		//获得购物车中的购物项的集合map
		Map<String, CartItem> cartItems = cart.getCartItems();
		for(Map.Entry<String, CartItem> entry:cartItems.entrySet()) {
			//取出每一个购物项
			CartItem cartItem = entry.getValue();
			//创建新的订单项
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(CommonsUtils.getUUID());
			orderItem.setCount(cartItem.getBuyNum());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			//将该订单项添加到订单的订单项集合中
			order.getOrderItems().add(orderItem);
		}
		productService.submitOrder(order);
		
		session.setAttribute("order", order);
		return "redirect:/order_info";
	}
	//确认订单
	@RequestMapping("confirmOrder")
	public void confirmOrder(Order order,HttpServletRequest request,HttpServletResponse response) throws IOException, AlipayApiException {
		//更新收货人信息
		productService.updateOrder(order);
		//
		// 获得 支付必须基本数据
				String orderid = request.getParameter("oid");
				//String money = order.getTotal()+"";
				String money = "0.01";
				// 银行
				String pd_FrpId = request.getParameter("pd_FrpId");
				//获得初始化的AlipayClient
				AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
				//设置请求参数
		        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		        alipayRequest.setReturnUrl(AlipayConfig.return_url);
		        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		        
		      //商户订单号，商户网站订单系统中唯一订单号，必填
		        String out_trade_no = orderid;
		        //付款金额，必填
		        String total_amount = money;
		        //订单名称，必填
		        String subject = "快乐";
		        //商品描述，可空
		        String body = "";
		        
		        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
		                + "\"total_amount\":\""+ total_amount +"\"," 
		                + "\"subject\":\""+ subject +"\"," 
		                + "\"body\":\""+ body +"\"," 
		                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		        
		        //请求
		        String result = alipayClient.pageExecute(alipayRequest).getBody();
		        
		        response.setContentType("text/html;charset="+AlipayConfig.charset);
		        response.getWriter().write(result);
		        response.getWriter().flush();
		        response.getWriter().close();

	}
	//获得我的订单
	@RequestMapping("/myOrders")
	public String myOrders(HttpServletRequest request) {
		HttpSession session = request.getSession();
		//判断用户是否已经登录
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return "redirect:/login";
		}
		
		//查询该用户所有订单信息
		List<Order> orderList = productService.findAllOrders(user.getUid());
		//循环所有订单
		if(orderList !=null) {
			for(Order order : orderList) {
				String oid = order.getOid();
				List<OrderItem> orderItem = productService.findAllOrderItemByOid(oid);
				order.getOrderItems().addAll(orderItem);
			}
		}
		request.setAttribute("orderList", orderList);
		return "order_list";
	}
	//未支付订单付款
	@RequestMapping("/payOrder")
	public String payOrder(Order order,HttpServletRequest request) {
		HttpSession session = request.getSession();
		//获得未支付订单
		String oid = order.getOid();
		 Order unorder = productService.findOrderByOid(oid);
		 
		 List<OrderItem> orderItem = productService.findAllOrderItemByOid(oid);
		 for(OrderItem orderItems : orderItem) {
			unorder.getOrderItems().add(orderItems); 
		 }
		 session.setAttribute("order", unorder);
		return "redirect:/order_uninfo";
	}
	@RequestMapping("/order_uninfo")
	public String order_uninfo() {
		return "order_uninfo";
	}
	
}
