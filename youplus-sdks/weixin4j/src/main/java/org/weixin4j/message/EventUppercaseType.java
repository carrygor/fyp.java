/*
 * 微信公众平台(JAVA) SDK
 *
 * Copyright (c) 2014, Ansitech Network Technology Co.,Ltd All rights reserved.
 * 
 * http://www.weixin4j.org/sdk/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.weixin4j.message;

/**
 * 事件类型
 *
 * @author weixin4j<weixin4j@ansitech.com>
 */
public enum EventUppercaseType {

    /**
     * 订阅
     */
    SUBSCRIBE("subscribe"),
    /**
     * 取消订阅
     */
    UNSUBSCRIBE("unsubscribe"),
    /**
     * 已关注用户扫描带参数二维码
     */
    SCAN("SCAN"),
    /**
     * 上报地理位置
     */
    LOCATION("location"),
    /**
     * 点击自定义菜单
     */
    CLICK("click"),
    /**
     * 查看菜单
     */
    VIEW("view"),
    /**
     * 扫码推事件
     */
    SCANCODE_PUSH("scancode_push"),
    /**
     * 扫码推事件
     */
    SCANCODE_WAITMSG("scancode_waitmsg"),
    /**
     * 弹出系统拍照发图的事件
     */
    PIC_SYSPHOTO("pic_sysphoto"),
    /**
     * 弹出拍照或者相册发图的事件
     */
    PIC_PHOTO_OR_ALBUM("pic_photo_or_album"),
    /**
     * 弹出微信相册发图器的事件
     */
    PIC_WEIXIN("pic_weixin"),
    /**
     * 弹出地理位置选择器的事件
     */
    LOCATION_SELECT("location_select");

    private String value = "";

    EventUppercaseType(String value) {
        this.value = value;
    }

    /**
     * @return the msgType
     */
    @Override
    public String toString() {
        return value;
    }
}
