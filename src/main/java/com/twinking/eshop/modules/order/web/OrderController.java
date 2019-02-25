package com.twinking.eshop.modules.order.web;

import com.twinking.eshop.common.base.entity.Page;
import com.twinking.eshop.common.base.web.BaseController;
import com.twinking.eshop.common.utils.DateUtils;
import com.twinking.eshop.common.utils.StringUtils;
import com.twinking.eshop.common.utils.excel.ExportExcelUtils;
import com.twinking.eshop.modules.order.constant.OrderConstant;
import com.twinking.eshop.modules.order.entity.Order;
import com.twinking.eshop.modules.order.entity.OrderDetail;
import com.twinking.eshop.modules.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:  订单控制层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/20 13 31
 */
@Controller
@RequestMapping("order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     */
    @RequestMapping(value = {"list", ""} )
    public String list(Order order, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Order> page = orderService.findPage(new Page<Order>(request, response), order);
        model.addAttribute("page", page);
        return "modules/order/orderList";
    }

    /**
     * 待处理订单列表
     */
    @RequestMapping(value = "todoList" )
    public String todoList(Order order, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Order> page = orderService.findTodoPage(new Page<Order>(request, response), order);
        model.addAttribute("page", page);
        return "modules/order/orderList";
    }

    /**
     * 未支付订单列表
     */
    @RequestMapping(value = "unFinishList" )
    public String unFinishList(Order order, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Order> page = orderService.findUnFinishPage(new Page<Order>(request, response), order);
        model.addAttribute("page", page);
        return "modules/order/orderList";
    }

    /**
     * 已完成订单列表
     */
    @RequestMapping(value = "finishList" )
    public String finishList(Order order, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Order> page = orderService.findFinishPage(new Page<Order>(request, response), order);
        model.addAttribute("page", page);
        return "modules/order/orderList";
    }

    /**
     * 查看某用户订单详情列表
     */
    @RequestMapping(value = "detail" )
    public String detail(OrderDetail orderDetail, Model model) {
        if(StringUtils.isBlank(orderDetail.getOrderId())){
            return "/error/404";
        }
        Order order = orderService.get(orderDetail.getOrderId());
        if(order == null){
            return "/error/404";
        }
        orderDetail.setOrder(order);
        OrderDetail od = orderService.findOrderDetail(orderDetail);
        model.addAttribute("orderDetail", od);
        return "modules/order/orderDetailList";
    }

    /**
     * 修改订单
     */
    @RequestMapping(value = "edit")
    public String edit(Order order, Model model) {
        if(StringUtils.isNotBlank(order.getId())){
            order = orderService.get(order.getId());
        }
        model.addAttribute("order", order);
        return "modules/order/orderForm";
    }

    /**
     * 保存表单页面
     */
    @RequestMapping(value = "save")
    public String save(Order order, Model model, RedirectAttributes redirectAttributes) throws Exception {
        if (!beanValidator(model, order)){
            return edit(order, model);
        }
        String message = orderService.saveOrder(order);
        addMessage(redirectAttributes, message);
        return "redirect:/order";
    }

    /**
     * 同意退款
     * @param order 订单id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "refund")
    public String refund(Order order, HttpServletRequest request, HttpServletResponse response,
                         RedirectAttributes redirectAttributes, Model model) {
        String message = orderService.refund(order);
        addMessage(redirectAttributes, message);

        if(OrderConstant.TODO_Order_List_type == order.getListType()){
            return todoList(new Order(), request,response,model);
        }else
        {
            return "redirect:/order";
        }
    }

    /**
     * 拒绝退款请求
     * @param order  订单id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "refuseRefund")
    public String refuseRefund(Order order, HttpServletRequest request, HttpServletResponse response,
                               RedirectAttributes redirectAttributes, Model model) {
       String message = orderService.refuseRefund(order);
        addMessage(redirectAttributes, message);

        if(OrderConstant.TODO_Order_List_type == order.getListType()){
            return todoList(new Order(), request,response,model);
        }else
        {
            return "redirect:/order";
        }
    }

    /**
     * 取消订单
     * @param order 订单id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "cancelOrder")
    public String cancelOrder(Order order, HttpServletRequest request, HttpServletResponse response,
                              RedirectAttributes redirectAttributes, Model model) {
        String message = orderService.cancelOrder(order);
        addMessage(redirectAttributes, message);

        if(OrderConstant.TODO_Order_List_type == order.getListType()){
            return todoList(new Order(), request,response,model);
        }else if(OrderConstant.Un_Finish_Order_List_type == order.getListType()) {
            return unFinishList(new Order(), request,response,model);
        }else {
            return "redirect:/order";
        }
    }

    /**
     * 拒绝取消订单
     * @param order 订单id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "refuseCancelOrder")
    public String refuseCancelOrder(Order order, HttpServletRequest request, HttpServletResponse response,
                                    RedirectAttributes redirectAttributes, Model model) {
        String message = orderService.refuseRefund(order);
        addMessage(redirectAttributes, message);

        if(OrderConstant.TODO_Order_List_type== order.getListType()){
            return todoList(new Order(), request,response,model);
        }else if(OrderConstant.Un_Finish_Order_List_type == order.getListType()) {
            return unFinishList(new Order(), request,response,model);
        }else {
            return "redirect:/order";
        }
    }

    /**
     * 删除数据
     */
    @RequestMapping(value = "delete")
    public String delete(Order order, HttpServletRequest request, HttpServletResponse response,
                         RedirectAttributes redirectAttributes, Model model) {
        orderService.delete(order);
        addMessage(redirectAttributes, "删除订单成功");

        if(OrderConstant.TODO_Order_List_type == order.getListType()){
            return todoList(new Order(), request,response,model);
        }else if(OrderConstant.Un_Finish_Order_List_type == order.getListType()) {
            return unFinishList(new Order(), request,response,model);
        }else if(OrderConstant.Finish_Order_List_type == order.getListType()) {
            return finishList(new Order(), request,response,model);
        }else {
            return "redirect:/order";
        }
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "deleteAll")
    public String deleteAll(String ids, HttpServletRequest request, HttpServletResponse response,
                            RedirectAttributes redirectAttributes, Model model) {
        int effect = orderService.deleteAll(ids);
        addMessage(redirectAttributes, "批量删除 " +effect+ " 条数据成功");
        return "redirect:/order";
    }

    /**
     * 导出excel文件
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(Order order, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "订单"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Order> page = orderService.findPage(new Page<Order>(request, response, -1), order);
            new ExportExcelUtils("订单", Order.class).setDataList(page.getList()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出订单记录失败！失败信息："+e.getMessage());
        }
        return "redirect:/order";
    }
    
}
