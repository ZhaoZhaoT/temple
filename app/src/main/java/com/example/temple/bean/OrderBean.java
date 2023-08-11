package com.example.temple.bean;

import java.io.Serializable;
import java.util.List;

public class OrderBean {
    private List<ContentBean> content;
    private int totalElements;
    private int totalPages;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public static class ContentBean implements Serializable {
        private String contractAddress;
        private String contractName;
        private String contractPhone;
        private Long createAt;
        private int firmId;
        private String goodName;
        private int id;
        private String idNo;
        private OrderExpressVOBean orderExpressVO;
        private OrderItemVOBean orderItemVO;
        private String outTradeNo;
        private Double payMoney;
        private String payType;
        private String remark;
        private String status;
        private String updatedAt;
        private Double sumMoney;
        private int userId;
        private String source;

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSource() {
            return source;
        }


        public String getContractAddress() {
            return contractAddress;
        }

        public void setContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
        }

        public String getContractName() {
            return contractName;
        }

        public void setContractName(String contractName) {
            this.contractName = contractName;
        }

        public String getContractPhone() {
            return contractPhone;
        }

        public void setContractPhone(String contractPhone) {
            this.contractPhone = contractPhone;
        }

        public Long getCreateAt() {
            return createAt;
        }

        public void setCreateAt(Long createAt) {
            this.createAt = createAt;
        }

        public int getFirmId() {
            return firmId;
        }

        public void setFirmId(int firmId) {
            this.firmId = firmId;
        }

        public String getGoodName() {
            return goodName;
        }

        public void setGoodName(String goodName) {
            this.goodName = goodName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public OrderExpressVOBean getOrderExpressVO() {
            return orderExpressVO;
        }

        public void setOrderExpressVO(OrderExpressVOBean orderExpressVO) {
            this.orderExpressVO = orderExpressVO;
        }

        public OrderItemVOBean getOrderItemVO() {
            return orderItemVO;
        }

        public void setOrderItemVO(OrderItemVOBean orderItemVO) {
            this.orderItemVO = orderItemVO;
        }

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public Double getPayMoney() {
            return payMoney;
        }

        public void setPayMoney(Double payMoney) {
            this.payMoney = payMoney;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Double getSumMoney() {
            return sumMoney;
        }

        public void setSumMoney(Double sumMoney) {
            this.sumMoney = sumMoney;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public static class BusinessFirmVOBean implements Serializable{
            private String cardPositive;
            private String cardReverse;
            private String createdAt;
            private String firmLicense;
            private String firmLogo;
            private String firmName;
            private int id;
            private String name;
            private String phone;
            private String sex;

            public String getCardPositive() {
                return cardPositive;
            }

            public void setCardPositive(String cardPositive) {
                this.cardPositive = cardPositive;
            }

            public String getCardReverse() {
                return cardReverse;
            }

            public void setCardReverse(String cardReverse) {
                this.cardReverse = cardReverse;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getFirmLicense() {
                return firmLicense;
            }

            public void setFirmLicense(String firmLicense) {
                this.firmLicense = firmLicense;
            }

            public String getFirmLogo() {
                return firmLogo;
            }

            public void setFirmLogo(String firmLogo) {
                this.firmLogo = firmLogo;
            }

            public String getFirmName() {
                return firmName;
            }

            public void setFirmName(String firmName) {
                this.firmName = firmName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }
        }

        public static class OrderExpressVOBean implements Serializable{
            private Long createdAt;
            private int createdBy;
            private String expressCompany;
            private String expressNo;
            private int orderId;

            public Long getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(Long createdAt) {
                this.createdAt = createdAt;
            }

            public int getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(int createdBy) {
                this.createdBy = createdBy;
            }

            public String getExpressCompany() {
                return expressCompany;
            }

            public void setExpressCompany(String expressCompany) {
                this.expressCompany = expressCompany;
            }

            public String getExpressNo() {
                return expressNo;
            }

            public void setExpressNo(String expressNo) {
                this.expressNo = expressNo;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }
        }

        public static class OrderItemVOBean implements Serializable{
            private int amount;
            private Long createdAt;
            private int goodId;
            private String goodName;
            private int goodSpecId;
            private int id;
            private Double money;
            private int orderId;
            private Double orgPrice;
            private Double price;
            private SpecVOBean specVO;
            private String typeEnum;
            private int userId;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public Long getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(Long createdAt) {
                this.createdAt = createdAt;
            }

            public int getGoodId() {
                return goodId;
            }

            public void setGoodId(int goodId) {
                this.goodId = goodId;
            }

            public String getGoodName() {
                return goodName;
            }

            public void setGoodName(String goodName) {
                this.goodName = goodName;
            }

            public int getGoodSpecId() {
                return goodSpecId;
            }

            public void setGoodSpecId(int goodSpecId) {
                this.goodSpecId = goodSpecId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public Double getMoney() {
                return money;
            }

            public void setMoney(Double money) {
                this.money = money;
            }

            public Double getOrgPrice() {
                return orgPrice;
            }

            public void setOrgPrice(Double orgPrice) {
                this.orgPrice = orgPrice;
            }

            public Double getPrice() {
                return price;
            }

            public void setPrice(Double price) {
                this.price = price;
            }

            public SpecVOBean getSpecVO() {
                return specVO;
            }

            public void setSpecVO(SpecVOBean specVO) {
                this.specVO = specVO;
            }

            public String getTypeEnum() {
                return typeEnum;
            }

            public void setTypeEnum(String typeEnum) {
                this.typeEnum = typeEnum;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public static class SpecVOBean implements Serializable{
                private String createdAt;
                private int goodId;
                private int id;
                private Double orgPrice;
                private Double price;
                private int sales;
                private String specCover;
                private String specName;
                private int stock;
                private String unit;

                public String getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(String createdAt) {
                    this.createdAt = createdAt;
                }

                public int getGoodId() {
                    return goodId;
                }

                public void setGoodId(int goodId) {
                    this.goodId = goodId;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public Double getOrgPrice() {
                    return orgPrice;
                }

                public void setOrgPrice(Double orgPrice) {
                    this.orgPrice = orgPrice;
                }

                public Double getPrice() {
                    return price;
                }

                public void setPrice(Double price) {
                    this.price = price;
                }

                public int getSales() {
                    return sales;
                }

                public void setSales(int sales) {
                    this.sales = sales;
                }

                public String getSpecCover() {
                    return specCover;
                }

                public void setSpecCover(String specCover) {
                    this.specCover = specCover;
                }

                public String getSpecName() {
                    return specName;
                }

                public void setSpecName(String specName) {
                    this.specName = specName;
                }

                public int getStock() {
                    return stock;
                }

                public void setStock(int stock) {
                    this.stock = stock;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }
            }
        }
    }
}
