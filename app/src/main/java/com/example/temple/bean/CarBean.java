package com.example.temple.bean;

import java.io.Serializable;
import java.util.List;

public class CarBean {
    private List<ContentBean> content;
    private int number;
    private int numberOfElements;
    private int size;
    private int totalElements;
    private int totalPages;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
        private int amount;
        private String createdAt;
        private int goodId;
        private int goodSpecId;
        private GoodsVOBean goodsVO;
        private int id;
        private String isPicked;
        private int userId;

        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

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

        public int getGoodSpecId() {
            return goodSpecId;
        }

        public void setGoodSpecId(int goodSpecId) {
            this.goodSpecId = goodSpecId;
        }

        public GoodsVOBean getGoodsVO() {
            return goodsVO;
        }

        public void setGoodsVO(GoodsVOBean goodsVO) {
            this.goodsVO = goodsVO;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIsPicked() {
            return isPicked;
        }

        public void setIsPicked(String isPicked) {
            this.isPicked = isPicked;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public static class GoodsVOBean implements Serializable{
            private BusinessFirmVOBean businessFirmVO;
            private String coverImg;
            private String description;
            private List<FilesVOBean> filesVO;
            private String goodNo;
            private GoodTypeVOBean goodTypeVO;
            private int id;
            private String isPicked;
            private String name;
            private double price;
            private int sales;
            private List<SpecVOBean> specVO;
            private String typeEnum;

            public BusinessFirmVOBean getBusinessFirmVO() {
                return businessFirmVO;
            }

            public void setBusinessFirmVO(BusinessFirmVOBean businessFirmVO) {
                this.businessFirmVO = businessFirmVO;
            }

            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<FilesVOBean> getFilesVO() {
                return filesVO;
            }

            public void setFilesVO(List<FilesVOBean> filesVO) {
                this.filesVO = filesVO;
            }

            public String getGoodNo() {
                return goodNo;
            }

            public void setGoodNo(String goodNo) {
                this.goodNo = goodNo;
            }

            public GoodTypeVOBean getGoodTypeVO() {
                return goodTypeVO;
            }

            public void setGoodTypeVO(GoodTypeVOBean goodTypeVO) {
                this.goodTypeVO = goodTypeVO;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIsPicked() {
                return isPicked;
            }

            public void setIsPicked(String isPicked) {
                this.isPicked = isPicked;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getSales() {
                return sales;
            }

            public void setSales(int sales) {
                this.sales = sales;
            }

            public List<SpecVOBean> getSpecVO() {
                return specVO;
            }

            public void setSpecVO(List<SpecVOBean> specVO) {
                this.specVO = specVO;
            }

            public String getTypeEnum() {
                return typeEnum;
            }

            public void setTypeEnum(String typeEnum) {
                this.typeEnum = typeEnum;
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

            public static class GoodTypeVOBean implements Serializable{
                private String createdAt;
                private int createdBy;
                private int id;
                private int sort;
                private String typeName;

                public String getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(String createdAt) {
                    this.createdAt = createdAt;
                }

                public int getCreatedBy() {
                    return createdBy;
                }

                public void setCreatedBy(int createdBy) {
                    this.createdBy = createdBy;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public String getTypeName() {
                    return typeName;
                }

                public void setTypeName(String typeName) {
                    this.typeName = typeName;
                }
            }

            public static class FilesVOBean implements Serializable{
                private String createdAt;
                private String filePath;
                private int goodId;
                private int id;

                public String getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(String createdAt) {
                    this.createdAt = createdAt;
                }

                public String getFilePath() {
                    return filePath;
                }

                public void setFilePath(String filePath) {
                    this.filePath = filePath;
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
            }

            public static class SpecVOBean implements Serializable{
                private String createdAt;
                private int goodId;
                private int buyAmount;
                private int id;
                private double orgPrice;
                private double price;
                private int sales;
                private String specCover;
                private String specName;
                private int stock;
                private String unit;

                public int getBuyAmount() {
                    return buyAmount;
                }

                public void setBuyAmount(int buyAmount) {
                    this.buyAmount = buyAmount;
                }

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

                public double getOrgPrice() {
                    return orgPrice;
                }

                public void setOrgPrice(double orgPrice) {
                    this.orgPrice = orgPrice;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
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
