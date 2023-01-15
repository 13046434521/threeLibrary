package com.jtl.duanzile_lib.bean;

import java.util.List;

/**
 * Author：jtl
 * Date：2023/1/11 11:05
 * Des：搜索
 */
public class SearchBean {

    private Integer code;
    private List<DataDTO> data;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataDTO {
        private InfoDTO info;
        private JokeDTO joke;
        private UserDTO user;

        public InfoDTO getInfo() {
            return info;
        }

        public void setInfo(InfoDTO info) {
            this.info = info;
        }

        public JokeDTO getJoke() {
            return joke;
        }

        public void setJoke(JokeDTO joke) {
            this.joke = joke;
        }

        public UserDTO getUser() {
            return user;
        }

        public void setUser(UserDTO user) {
            this.user = user;
        }

        public static class InfoDTO {
            private Integer commentNum;
            private Integer disLikeNum;
            private Boolean isAttention;
            private Boolean isLike;
            private Boolean isUnlike;
            private Integer likeNum;
            private Integer shareNum;

            public Integer getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(Integer commentNum) {
                this.commentNum = commentNum;
            }

            public Integer getDisLikeNum() {
                return disLikeNum;
            }

            public void setDisLikeNum(Integer disLikeNum) {
                this.disLikeNum = disLikeNum;
            }

            public Boolean getIsAttention() {
                return isAttention;
            }

            public void setIsAttention(Boolean isAttention) {
                this.isAttention = isAttention;
            }

            public Boolean getIsLike() {
                return isLike;
            }

            public void setIsLike(Boolean isLike) {
                this.isLike = isLike;
            }

            public Boolean getIsUnlike() {
                return isUnlike;
            }

            public void setIsUnlike(Boolean isUnlike) {
                this.isUnlike = isUnlike;
            }

            public Integer getLikeNum() {
                return likeNum;
            }

            public void setLikeNum(Integer likeNum) {
                this.likeNum = likeNum;
            }

            public Integer getShareNum() {
                return shareNum;
            }

            public void setShareNum(Integer shareNum) {
                this.shareNum = shareNum;
            }
        }

        public static class JokeDTO {
            private String addTime;
            private String audit_msg;
            private String content;
            private Boolean hot;
            private String imageSize;
            private String imageUrl;
            private Integer jokesId;
            private String latitudeLongitude;
            private String showAddress;
            private String thumbUrl;
            private Integer type;
            private Integer userId;
            private String videoSize;
            private Integer videoTime;
            private String videoUrl;

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getAudit_msg() {
                return audit_msg;
            }

            public void setAudit_msg(String audit_msg) {
                this.audit_msg = audit_msg;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Boolean getHot() {
                return hot;
            }

            public void setHot(Boolean hot) {
                this.hot = hot;
            }

            public String getImageSize() {
                return imageSize;
            }

            public void setImageSize(String imageSize) {
                this.imageSize = imageSize;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public Integer getJokesId() {
                return jokesId;
            }

            public void setJokesId(Integer jokesId) {
                this.jokesId = jokesId;
            }

            public String getLatitudeLongitude() {
                return latitudeLongitude;
            }

            public void setLatitudeLongitude(String latitudeLongitude) {
                this.latitudeLongitude = latitudeLongitude;
            }

            public String getShowAddress() {
                return showAddress;
            }

            public void setShowAddress(String showAddress) {
                this.showAddress = showAddress;
            }

            public String getThumbUrl() {
                return thumbUrl;
            }

            public void setThumbUrl(String thumbUrl) {
                this.thumbUrl = thumbUrl;
            }

            public Integer getType() {
                return type;
            }

            public void setType(Integer type) {
                this.type = type;
            }

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }

            public String getVideoSize() {
                return videoSize;
            }

            public void setVideoSize(String videoSize) {
                this.videoSize = videoSize;
            }

            public Integer getVideoTime() {
                return videoTime;
            }

            public void setVideoTime(Integer videoTime) {
                this.videoTime = videoTime;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }
        }

        public static class UserDTO {
            private String avatar;
            private String nickName;
            private String signature;
            private Integer userId;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }
        }
    }
}
