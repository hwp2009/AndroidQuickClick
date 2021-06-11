package com.yulai.quickclicklib.aspect;

import android.util.Log;
import android.view.View;

import com.yulai.quickclicklib.R;
import com.yulai.quickclicklib.annotation.SingleClick;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Calendar;

/**
 * @author yulai
 * @time:
 */
@Aspect
public class SingleClickAspectJ {


    private static final String TAG = "SingleClickAspectJ";
    static final int KEY= R.id.click_id;
    //切入点是任何带有 SingleClick注解的 方法
    @Pointcut("execution(@ com.yulai.quickclicklib.annotation.SingleClick * *(..))")
    public void executionSingleClick(){
    }

    @Around("executionSingleClick()")
    public void executionSingleClickAround(ProceedingJoinPoint joinPoint) throws Throwable {

        View view = null;

        //Object[] getArgs(); //返回被通知方法参数列表

        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        SingleClick singleClick = signature.getMethod().getAnnotation(SingleClick.class);

        Object[] args = joinPoint.getArgs(); //一般点击事件的参数会有一个view 拿到这个view的话我们就可以做我们自己的逻辑了

        for (Object object:args){

            if(object instanceof View){

                view =  (View)object;
            }
        }

        if(view!=null){

            Object tag = view.getTag(KEY); //第一次一定会为null
            long lastClickTime= tag==null?0: (long) tag;
            long currentTime= Calendar.getInstance().getTimeInMillis();

            if(currentTime-lastClickTime>=singleClick.clickInterval()){
                //如果大于指定的时间的话则执行方法 也就是joinPoing.processd() 不然的话不会被执行的
                view.setTag(KEY,currentTime);
                Log.d(TAG, "executionSingleClickAround: currentTime  "+currentTime);
                joinPoint.proceed();
            }
        }

    }

}
