package com.kkosoonnae.jpa.enu;

import lombok.Getter;

@Getter
public enum StyleType {
    SHEET("양컷",50000,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNSKS5d0V4JWwqqql0yH0NmvSmZ1mlf344mw&s"),
    JINDO("진도컷",30000,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzSwlo75zAkX_Z-8stkZZHr7MBsKaYTddS8McZlDuPWK2BDfHnEaGrl1yeyw&s"),
    BEAR("곰돌이컷",28000,"https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjExMTVfMTcx%2FMDAxNjY4NTE2NjA4OTkx.PzHsJsztLQxG0veozLaO5rbUt_0ngMSrM1zLVZ8Rnhwg.5DKiixYfMa-rK9G_tNVwet2KeXaHS0qGLScsHCGrVL4g.JPEG.younsemi1%2FEA5F58A8-3EDC-4870-ACC1-75FAA1876FCC.jpg&type=ff332_332"),
    BABY("베이비컷",20000, "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzAzMDNfMTE0%2FMDAxNjc3ODU0NTA4NjMw.7IP0dxQohtPhZew5Fkg7fekiUpLyI_BrzdENp9vdhr8g.vzmbZWkSnpTwcjmXdpoGH_AAYOFZF0buLK31xJ00wecg.JPEG.hobbangggg%2FIMG_7821.jpg&type=ff332_332"),
    SEAL("물개컷",18000,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRehR6y6k1z7Ys14mZIXTQ1er4oC_RSdqb4sg&s"),
    SUMMER("썸머컷",25000,"https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20160530_36%2F5813652_1464592443269H3TD3_JPEG%2F20160530_161043_edit.jpg&type=ff332_332"),
    HIBA("하이바컷",30000,"https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTExMTZfMjMx%2FMDAxNjM2OTg5NzIzNjMy.yHh_W0wU-ihCFrXo9woxC5tKHZL6R0MZ_2tUC5W5Dt4g.K9BsGc-KoIYpeP4KKsxluU7TG6iaOEiO_aKMTJiP8jkg.JPEG.wjdgml7770%2FKakaoTalk_20211114_223127249_02.jpg&type=ff332_332"),
    MIRI("3미리컷", 15000,"https://search.pstatic.net/common/?src=http%3A%2F%2Fimage.nmv.naver.net%2Fblogucc28%2F2017%2F04%2F12%2F1611%2Ff1ffcdb0733764310d3bf0f664dc6a576956_ugcvideo_270P_01_origin_logo.jpg&type=ofullfill340_600_png"),
    TADDYBEAR("테디베어컷",30000,"https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA3MjhfMjg0%2FMDAxNjI3NDQzNjQ0ODIw.T77GTvmOeVSoAKFanoXN6JUj_AmWKVIQ_S2xKiGKtzwg.aRyWzBBH4ISn0P7I7iA87zAUf_DRBpM-LRAuk705jiog.JPEG.hrejoice%2F4441E9A2-9700-4B5D-B462-9453673F3D3E-8817-000008946D0CF815.jpg&type=a340"),
    HALFSEAL("하프물범컷",13000,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTI1B58N44Sh9S36F3Txaw_yqTee35_2OIfnA&s");

    private final String name;
    private final int price;
    private final String imageUrl;
    StyleType(String name, int price, String imageUrl) {
        this.name=name;
        this.price=price;
        this.imageUrl=imageUrl;
    }

}
