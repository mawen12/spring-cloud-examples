package com.mawen.ribbon.context;

/**
 * @author <a href="mailto:1181963012mw@gmail.com">mawen12</a>
 * @since 2023/3/19
 */
public class RibbonRequestContextHolder {

    private static ThreadLocal<RibbonRequestContext> holder = new ThreadLocal<RibbonRequestContext>() {
        @Override
        protected RibbonRequestContext initialValue() {
            return new RibbonRequestContext();
        }
    };

    public static RibbonRequestContext getCurrentContext() {
        return holder.get();
    }

    public static void setCurrentContext(RibbonRequestContext context) {
        holder.set(context);
    }

    public static void clear() {
        holder.remove();
    }
}
