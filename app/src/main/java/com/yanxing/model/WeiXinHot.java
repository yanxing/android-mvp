package com.yanxing.model;

import java.util.List;

/**
 * Created by lishuangxiang on 2016/11/22.
 */

public class WeiXinHot {


    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-09-18","title":"3招让你的旅行照片赞爆朋友圈！收藏起来旅行正好能用~","description":"无界咖啡馆","picUrl":"http://mmbiz.qpic.cn/mmbiz/adCVBBIDdtvLiaXcBStvUTLNXYmd2icdoTwjDeH5chgtDYqzMJzyEMG46wsIb3rBOyygbUpheby0keBZ6wwZpUJQ/0?wx_fmt=jpeg","url":"http://mp.weixin.qq.com/s?__biz=MzIyMDE2MjA1OQ==&mid=408329667&idx=1&sn=663e833e84d008039d51f03671d326ad&scene=24&srcid=0608AeIxjLCUSkEkAVm4fcwO#rd"},{"ctime":"2016-09-06","title":"国家旅游局：第二季度旅行社组织出境旅游人次同比17.32%","description":"环球旅讯","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-7985329.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MTEzMzIzODIyMQ==&idx=2&mid=2654731730&sn=157280aba3a807a2a91edb6c70897b8e"},{"ctime":"2016-09-05","title":"青春就是一场旅行","description":"国内旅游攻略","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-7968791.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MjM5NjIzNjY5NA==&idx=1&mid=2653426853&sn=17901fcacbed4227b55562222abc1b43"},{"ctime":"2016-09-04","title":"50个理由告诉你，我们为什么要去旅行","description":"背包旅行摄影","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-7951755.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MjM5MjIyMzIyMA==&idx=3&mid=2651984550&sn=e5ecc687d79ed418aae76100794d8454"},{"ctime":"2016-09-04","title":"为让老人好好游山玩水，国家给旅行社立规矩！孝顺的儿女都会替父母看看","description":"长江日报","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-7938022.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MjM5ODAyNTcwMA==&idx=3&mid=2651742551&sn=1b2ed84a3e94d02cc187d03cb8944f0e"},{"ctime":"2016-09-04","title":"35年的蜜月旅行，到底能浪漫成什么样子？","description":"旅行指南","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-7932371.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MjM5NjA5NzgwMA==&idx=3&mid=2652625114&sn=9ae53ce0343b0b940ff4d12ce13611cf"},{"ctime":"2016-09-03","title":"QStory|飞鱼秀喻舟：旅行就像给爱情照X光","description":"穷游网","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-7919283.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MTc2MTIzOTg4MQ==&idx=1&mid=2652244242&sn=c6c4c0e7a45c95e78f06a7f237440180"},{"ctime":"2016-09-02","title":"如果你想要去旅行，就关注这个号","description":"微杂志","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-7867549.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=NzgzNDc5ODQx&idx=3&mid=2651798597&sn=53d61877626be8fcc40f87e24de2c29a"},{"ctime":"2016-09-02","title":"喜欢旅行的人，人品都不会太差！","description":"好旅游","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-7880490.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MjM5MTEzMDk1Mw==&idx=2&mid=2653160373&sn=424dc07d500a9679db9c27e43e417df6"},{"ctime":"2016-09-01","title":"出门旅行中的32个小窍门，很实用喔！","description":"低碳生活小窍门","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-7858188.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MzAxNjUzNDgwNA==&idx=1&mid=2653301197&sn=420e18d13af218309f09664caab35aaa"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2016-09-18
         * title : 3招让你的旅行照片赞爆朋友圈！收藏起来旅行正好能用~
         * description : 无界咖啡馆
         * picUrl : http://mmbiz.qpic.cn/mmbiz/adCVBBIDdtvLiaXcBStvUTLNXYmd2icdoTwjDeH5chgtDYqzMJzyEMG46wsIb3rBOyygbUpheby0keBZ6wwZpUJQ/0?wx_fmt=jpeg
         * url : http://mp.weixin.qq.com/s?__biz=MzIyMDE2MjA1OQ==&mid=408329667&idx=1&sn=663e833e84d008039d51f03671d326ad&scene=24&srcid=0608AeIxjLCUSkEkAVm4fcwO#rd
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
