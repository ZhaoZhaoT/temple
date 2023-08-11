package com.example.temple.datatype;

import android.text.TextUtils;

public  final class DataMatching {


    /**
     * 康宁类型
     */
    public enum KangNingType {
        ZERO("0", "ZERO"),//求子
        ONE("1", "ONE"),//姻缘
        TWO("2", "TWO"),//平安
        THREE("3", "THREE"),//学业
        FOUR("4", "FOUR"),//财富
        FIVE("5", "FIVE"),//健康
        ;

        private String type;
        private String desc;

        private KangNingType(String type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public String getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }

        /**
         * @param type 提醒类型
         * @return
         */
        public static String getKangNingDesc(String type) {
            if (TextUtils.isEmpty(type)) {
                return KangNingType.ZERO.getDesc();
            }
            KangNingType[] remindType = KangNingType.values();
            for (KangNingType t : remindType) {
                if (t.getType().equals(type)) {
                    return t.getDesc();
                }
            }
            return KangNingType.ZERO.getDesc();
        }
    }
}
