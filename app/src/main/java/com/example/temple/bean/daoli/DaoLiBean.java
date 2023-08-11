package com.example.temple.bean.daoli;

public class DaoLiBean {
    int status;

    DaoLiInfoBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DaoLiInfoBean getResult() {
        return result;
    }

    public void setResult(DaoLiInfoBean result) {
        this.result = result;
    }


    public class DaoLiInfoBean {
        DaoLiHuangLiInfoBean huangli;
        private String ganzhishi;
        private String shengxiao;

        private String year;
        private String month;
        private String day;
        private String week;
        private String lunarmonth;
        private String lunarday;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getLunarmonth() {
            return lunarmonth;
        }

        public void setLunarmonth(String lunarmonth) {
            this.lunarmonth = lunarmonth;
        }

        public String getLunarday() {
            return lunarday;
        }

        public void setLunarday(String lunarday) {
            this.lunarday = lunarday;
        }

        public String getShengxiao() {
            return shengxiao;
        }

        public void setShengxiao(String shengxiao) {
            this.shengxiao = shengxiao;
        }

        public String getGanzhishi() {
            return ganzhishi;
        }

        public void setGanzhishi(String ganzhishi) {
            this.ganzhishi = ganzhishi;
        }

        public DaoLiHuangLiInfoBean getHuangli() {
            return huangli;
        }

        public void setHuangli(DaoLiHuangLiInfoBean huangli) {
            this.huangli = huangli;
        }

        public class DaoLiHuangLiInfoBean {
            private String yi[];//今日所宜
            private String ji[];//今日所忌
            private String chong;//冲
            private String sha;//煞
            private String jishenyiqu;//吉神宜趋
            private String xishen;//喜神
            private String fushen;//福神
            private String caishen;//财神
            private String taishen;//天神

            private String suici[];//岁次
            private String wuxing;//五行

            public String getWuxing() {
                return wuxing;
            }

            public void setWuxing(String wuxing) {
                this.wuxing = wuxing;
            }


            public String getXishen() {
                return xishen;
            }

            public void setXishen(String xishen) {
                this.xishen = xishen;
            }

            public String getFushen() {
                return fushen;
            }

            public void setFushen(String fushen) {
                this.fushen = fushen;
            }

            public String getCaishen() {
                return caishen;
            }

            public void setCaishen(String caishen) {
                this.caishen = caishen;
            }

            public String getTaishen() {
                return taishen;
            }

            public void setTaishen(String taishen) {
                this.taishen = taishen;
            }

            public String[] getYi() {
                return yi;
            }

            public void setYi(String[] yi) {
                this.yi = yi;
            }

            public String[] getJi() {
                return ji;
            }

            public void setJi(String[] ji) {
                this.ji = ji;
            }

            public String[] getSuici() {
                return suici;
            }

            public void setSuici(String[] suici) {
                this.suici = suici;
            }

            public String getChong() {
                return chong;
            }

            public void setChong(String chong) {
                this.chong = chong;
            }

            public String getSha() {
                return sha;
            }

            public void setSha(String sha) {
                this.sha = sha;
            }

            public String getJishenyiqu() {
                return jishenyiqu;
            }

            public void setJishenyiqu(String jishenyiqu) {
                this.jishenyiqu = jishenyiqu;
            }
        }


    }

}
