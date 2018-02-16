package cn.com.xiaohanyu.echarts4j.model.base;

/**
 * Created by 严汉羽 on 2017/11/17.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 基本的颜色类，暂不支持渐变色
 */
public class Color {
    /**
     * 红色
     */
    private Byte r = 0;

    /**
     * 绿色
     */
    private Byte g = 0;

    /**
     * 蓝色
     */
    private Byte b = 0;

    /**
     * 透明度
     */
    private Byte i = null;

    /**
     * 默认构造函数
     */
    public Color() {

    }

    /**
     * 带上颜色配置
     * @param r 红色
     * @param g 绿色
     * @param b 蓝色
     */
    public Color(Byte r, byte g, byte b) {
        this.r = r;
        this.b = b;
        this.g = g;
    }

    /**
     * 带上颜色配置
     * @param r 红色
     * @param g 绿色
     * @param b 蓝色
     * @param i 透明度
     */
    public Color(Byte r, byte g, byte b, byte i) {
        this.r = r;
        this.b = b;
        this.g = g;
        this.i = i;
    }

    public Byte getR() {
        return r;
    }

    public Color setR(Byte r) {
        this.r = r;
        return this;
    }

    public Byte getG() {
        return g;
    }

    public Color setG(Byte g) {
        this.g = g;
        return this;
    }

    public Byte getB() {
        return b;
    }

    public Color setB(Byte b) {
        this.b = b;
        return this;
    }

    public Byte getI() {
        return i;
    }

    public Color setI(Byte i) {
        this.i = i;
        return this;
    }

    /**
     * 组装成对象
     * @return
     */
    public Object toMap() {
        return toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("#");
        stringBuilder.append(Integer.toHexString(r));
        stringBuilder.append(Integer.toHexString(g));
        stringBuilder.append(Integer.toHexString(b));
        if (i != null) {
            stringBuilder.append(Integer.toHexString(i));
        }
        return stringBuilder.toString();
    }
}
