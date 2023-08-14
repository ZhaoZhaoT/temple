package com.example.temple.bean;

import java.util.List;

public class HomeBean extends BaseResponse {

    private List<BannerListVOBean> bannerListVO;
    private List<InfoBean> informListVO;
    private GoodRankingVOBean topData;
    private GoodVOBean thingData;

    public List<BannerListVOBean> getBannerListVO() {
        return bannerListVO;
    }

    public void setBannerListVO(List<BannerListVOBean> bannerListVO) {
        this.bannerListVO = bannerListVO;
    }

    public List<InfoBean> getInformListVO() {
        return informListVO;
    }

    public void setInformListVO(List<InfoBean> informListVO) {
        this.informListVO = informListVO;
    }

    public GoodRankingVOBean getTopData() {
        return topData;
    }

    public void setTopData(GoodRankingVOBean topData) {
        this.topData = topData;
    }

    public GoodVOBean getThingData() {
        return thingData;
    }

    public void setThingData(GoodVOBean thingData) {
        this.thingData = thingData;
    }

    public static class GoodRankingVOBean {
        private List<ContentBean> content;
        private PageableBean pageable;
        private boolean last;
        private int totalPages;
        private int totalElements;
        private boolean first;
        private int numberOfElements;
        private int size;
        private int number;
        private boolean empty;

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public PageableBean getPageable() {
            return pageable;
        }

        public void setPageable(PageableBean pageable) {
            this.pageable = pageable;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
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

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public boolean isEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

        public static class PageableBean {
            private int pageNumber;
            private int pageSize;
            private int offset;
            private boolean unpaged;
            private boolean paged;

            public int getPageNumber() {
                return pageNumber;
            }

            public void setPageNumber(int pageNumber) {
                this.pageNumber = pageNumber;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public boolean isUnpaged() {
                return unpaged;
            }

            public void setUnpaged(boolean unpaged) {
                this.unpaged = unpaged;
            }

            public boolean isPaged() {
                return paged;
            }

            public void setPaged(boolean paged) {
                this.paged = paged;
            }

        }

        public static class ContentBean {
            private int id;
            private String goodNo;
            private String name;
            private String coverImg;
            private String description;
            private GoodTypeVO goodTypeVO;
            private int sales;
            private String price;
            private String typeEnum;
            private String isPicked;

            public GoodTypeVO getGoodTypeVO() {
                return goodTypeVO;
            }

            public void setGoodTypeVO(GoodTypeVO goodTypeVO) {
                this.goodTypeVO = goodTypeVO;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGoodNo() {
                return goodNo;
            }

            public void setGoodNo(String goodNo) {
                this.goodNo = goodNo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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


            public int getSales() {
                return sales;
            }

            public void setSales(int sales) {
                this.sales = sales;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTypeEnum() {
                return typeEnum;
            }

            public void setTypeEnum(String typeEnum) {
                this.typeEnum = typeEnum;
            }

            public String getIsPicked() {
                return isPicked;
            }

            public void setIsPicked(String isPicked) {
                this.isPicked = isPicked;
            }

            public static class GoodTypeVO {
                private int id;
                private String typeName;
                private String sort;
                private String createdBy;
                private String createdAt;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTypeName() {
                    return typeName;
                }

                public void setTypeName(String typeName) {
                    this.typeName = typeName;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getCreatedBy() {
                    return createdBy;
                }

                public void setCreatedBy(String createdBy) {
                    this.createdBy = createdBy;
                }

                public String getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(String createdAt) {
                    this.createdAt = createdAt;
                }
            }
        }
    }

    public static class GoodVOBean {
        private List<ContentBean> content;
        private PageableBean pageable;
        private boolean last;
        private int totalPages;
        private int totalElements;
        private boolean first;
        private int numberOfElements;
        private int size;
        private int number;
        private boolean empty;

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public PageableBean getPageable() {
            return pageable;
        }

        public void setPageable(PageableBean pageable) {
            this.pageable = pageable;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
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

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public boolean isEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

        public static class PageableBean {
            private int pageNumber;
            private int pageSize;
            private int offset;
            private boolean unpaged;
            private boolean paged;

            public int getPageNumber() {
                return pageNumber;
            }

            public void setPageNumber(int pageNumber) {
                this.pageNumber = pageNumber;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public boolean isUnpaged() {
                return unpaged;
            }

            public void setUnpaged(boolean unpaged) {
                this.unpaged = unpaged;
            }

            public boolean isPaged() {
                return paged;
            }

            public void setPaged(boolean paged) {
                this.paged = paged;
            }
        }

        public static class ContentBean {
            private int id;
            private String goodNo;
            private String name;
            private String coverImg;
            private String description;
            private GoodRankingVOBean.ContentBean.GoodTypeVO goodTypeVO;
            private int sales;
            private String price;
            private String typeEnum;
            private String isPicked;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGoodNo() {
                return goodNo;
            }

            public void setGoodNo(String goodNo) {
                this.goodNo = goodNo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public GoodRankingVOBean.ContentBean.GoodTypeVO getGoodTypeVO() {
                return goodTypeVO;
            }

            public void setGoodTypeVO(GoodRankingVOBean.ContentBean.GoodTypeVO goodTypeVO) {
                this.goodTypeVO = goodTypeVO;
            }

            public int getSales() {
                return sales;
            }

            public void setSales(int sales) {
                this.sales = sales;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTypeEnum() {
                return typeEnum;
            }

            public void setTypeEnum(String typeEnum) {
                this.typeEnum = typeEnum;
            }

            public String getIsPicked() {
                return isPicked;
            }

            public void setIsPicked(String isPicked) {
                this.isPicked = isPicked;
            }
        }
    }

    public static class BannerListVOBean {
        private int id;
        private String imagePath;
        private String bannerDesc;
        private int goodId;
        private int createdBy;
        private long createdAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getBannerDesc() {
            return bannerDesc;
        }

        public void setBannerDesc(String bannerDesc) {
            this.bannerDesc = bannerDesc;
        }

        public int getGoodId() {
            return goodId;
        }

        public void setGoodId(int goodId) {
            this.goodId = goodId;
        }

        public int getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(int createdBy) {
            this.createdBy = createdBy;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
        }
    }

    public static class InfoBean {

        private int id;
        private String content;
        private String detail;
        private int createdBy;
        private String coverImg;
        private long createdAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(int createdBy) {
            this.createdBy = createdBy;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
        }
    }

}
