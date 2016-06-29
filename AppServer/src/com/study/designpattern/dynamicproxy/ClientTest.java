package com.study.designpattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Calendar;
import java.util.GregorianCalendar;

//抽象UserDAO：抽象主题角色  
interface AbstractUserDAO {  
    public Boolean findUserById(String userId);  
}  
  
//抽象DocumentDAO：抽象主题角色  
interface AbstractDocumentDAO {  
    public Boolean deleteDocumentById(String documentId);  
}  
  
//具体UserDAO类：真实主题角色  
class UserDAO implements AbstractUserDAO {  
    public Boolean findUserById(String userId) {  
        if (userId.equalsIgnoreCase("张无忌")) {  
            System.out.println("查询ID为" + userId + "的用户信息成功！");  
            return true;  
        }  
        else {  
            System.out.println("查询ID为" + userId + "的用户信息失败！");  
            return false;  
        }  
    }  
}  
  
//具体DocumentDAO类：真实主题角色  
class DocumentDAO implements AbstractDocumentDAO {  
    public Boolean deleteDocumentById(String documentId) {  
        if (documentId.equalsIgnoreCase("D001")) {  
            System.out.println("删除ID为" + documentId + "的文档信息成功！");  
            return true;  
        }  
        else {  
            System.out.println("删除ID为" + documentId + "的文档信息失败！");  
            return false;  
        }  
    }  
}  
  
//自定义请求处理程序类  
class DAOLogHandler implements InvocationHandler {  
    private Calendar calendar;  
    private Object object;  
      
    public DAOLogHandler() {      
    }  
      
    //自定义有参构造函数，用于注入一个需要提供代理的真实主题对象  
    public DAOLogHandler(Object object) {  
        this.object = object;  
    }  
      
    //实现invoke()方法，调用在真实主题类中定义的方法  
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {  
        beforeInvoke();  
        Object result = method.invoke(object, args); //转发调用  
        afterInvoke();  
        return null;  
    }  
  
    //记录方法调用时间  
    public void beforeInvoke(){  
        calendar = new GregorianCalendar();  
        int hour = calendar.get(Calendar.HOUR_OF_DAY);  
        int minute = calendar.get(Calendar.MINUTE);  
        int second = calendar.get(Calendar.SECOND);  
        String time = hour + ":" + minute + ":" + second;  
        System.out.println("调用时间：" + time);  
    }  
  
    public void afterInvoke(){  
        System.out.println("方法调用结束！" );  
    }  
}  

public class ClientTest {  
    public static void main(String args[]) {  
        InvocationHandler handler = null;  
          
        AbstractUserDAO userDAO = new UserDAO();  
        handler = new DAOLogHandler(userDAO);  
        AbstractUserDAO userProxy = null;  
        //动态创建代理对象，用于代理一个AbstractUserDAO类型的真实主题对象  
        userProxy = (AbstractUserDAO)Proxy.newProxyInstance(AbstractUserDAO.class.getClassLoader(), new Class[]{AbstractUserDAO.class}, handler);  
        userProxy.findUserById("张无忌"); //调用代理对象的业务方法  
      
        System.out.println("------------------------------");  
      
        AbstractDocumentDAO docDAO = new DocumentDAO();  
        handler = new DAOLogHandler(docDAO);  
        AbstractDocumentDAO docProxy = null;  
//动态创建代理对象，用于代理一个AbstractDocumentDAO类型的真实主题对象  
        docProxy = (AbstractDocumentDAO)Proxy.newProxyInstance(AbstractDocumentDAO.class.getClassLoader(), new Class[]{AbstractDocumentDAO.class}, handler);  
        docProxy.deleteDocumentById("D002"); //调用代理对象的业务方法  
    }   
} 

