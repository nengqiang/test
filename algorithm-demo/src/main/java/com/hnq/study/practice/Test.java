package com.hnq.study.practice;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author henengqiang
 * @date 2019/08/08
 */
public class Test {

    public static void main(String[] args) {
        String data = "{\n" +
                "'code': 'S_OK',\n" +
                "'var': [{\n" +
                "'id': '176:1tbisA1II1UMLGv2HAAAsi_2275:170d2:h:q:8',\n" +
                "'fid': 18,\n" +
                "'size': 103309,\n" +
                "'from': '\"PayPal\" <paypal@mail.paypal.com>',\n" +
                "'to': 'jane1229@163.com',\n" +
                "'subject': 'Guo Ying, 您的购物有保障 <AD>',\n" +
                "    'sentDate': new Date(2019, 9, 8, 9, 22, 2),\n" +
                "    'receivedDate': new Date(2019, 9, 8, 12, 42, 35),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'hasTag': true\n" +
                "    },\n" +
                "    'ctrls': {\n" +
                "    'SysTag': '%27015%'\n" +
                "    },\n" +
                "    'hmid': '<HP2v40000016da8f56fc8966f77f4bbc786c8065@mail.paypal.com>',\n" +
                "    'tag': ['%27015%']\n" +
                "    }, {\n" +
                "    'id': '187:1tbiuwRAI1PAEc-bzgAAsE_473:2041:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 9450,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2019, 8, 30, 20, 38, 17),\n" +
                "    'receivedDate': new Date(2019, 8, 30, 20, 38, 17),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1-0310-20190930193526-1732287-4951587829>',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '45:1tbiLQRAI1SIepiauwAAsV_473:2065:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 9486,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2019, 8, 30, 20, 37, 55),\n" +
                "    'receivedDate': new Date(2019, 8, 30, 20, 37, 55),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1-0310-20190930193526-1732288-2523299825>',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '45:1tbiLRr8I1SIdllJDAAAse_471:22dd:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 10115,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2019, 6, 25, 19, 10, 23),\n" +
                "    'receivedDate': new Date(2019, 6, 25, 19, 10, 28),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1-0310-20190725130337-2751479-7848913269>',\n" +
                "    'rootid': 'xtbBFBKvI1aDxuPUcgAAsc',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '437:xtbBtR8SI1wAu1a-8gAAsn_471:22b9:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 10080,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2019, 5, 25, 22, 48, 44),\n" +
                "    'receivedDate': new Date(2019, 5, 25, 22, 48, 45),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'emid': '1tbiLRfeI1SIdBi6rwAAsi',\n" +
                "    'hmid': '<1-0310-20190625202546-9122069-8798838193>',\n" +
                "    'rootid': 'xtbBFBKvI1aDxuPUcgAAsc',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '177:1tbisRHGI1XlhzBiYQAAsB_632:80a:h:b:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 3644,\n" +
                "    'from': '\"光大银行申卡中心商必江\" <hairswarm@318pfb.cn>',\n" +
                "    'to': '\"13429181586\\n\" <13429181586@163.com>',\n" +
                "    'subject': '13429181586，信用卡申请，就选光大银行信用卡，手续简单额度高，活动丰富可免年费，更有积分换大礼商必江，即刻申请开启品质人生',\n" +
                "    'sentDate': new Date(2019, 5, 1, 9, 24, 17),\n" +
                "    'receivedDate': new Date(2019, 5, 1, 9, 24, 19),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'accounts': ['13429181586@163.com'],\n" +
                "    'hmid': '<2019060109241731371@318pfb.cn>',\n" +
                "    'rootid': ''\n" +
                "    }, {\n" +
                "    'id': '276:xtbBFAXAI1aD11CKsAABsb_470:2241:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 9957,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2019, 4, 26, 19, 43, 11),\n" +
                "    'receivedDate': new Date(2019, 4, 26, 19, 43, 13),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1-0310-20190525173749-6547617-2609292124>',\n" +
                "    'rootid': 'xtbBFBKvI1aDxuPUcgAAsc',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '437:xtbBtRojI1wAu-549gAAs4_46f:22b9:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 10076,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2019, 3, 26, 2, 8, 37),\n" +
                "    'receivedDate': new Date(2019, 3, 26, 2, 8, 39),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'emid': '1tbiUAOhI1WBha5RSQAAs2',\n" +
                "    'hmid': '<1-0310-20190425225127-4178918-8080446045>',\n" +
                "    'rootid': 'xtbBFBKvI1aDxuPUcgAAsc',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '43:1tbiKxZrI1QHNn9JewAAsd_1e8d:7e93:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 943114,\n" +
                "    'from': '\"cleverbridge / Parallels International GmbH\" <no-reply@cleverbridge.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '参考号 158866052：您订阅的 Parallels Desktop for Mac Pro Edition（1 年） 款项已到期',\n" +
                "    'sentDate': new Date(2019, 2, 2, 11, 55, 34),\n" +
                "    'receivedDate': new Date(2019, 2, 2, 11, 56, 7),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'realAttached': true,\n" +
                "    'report': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1.5897618d74dacd0713bc@app12>',\n" +
                "    'rootid': ''\n" +
                "    }, {\n" +
                "    'id': '43:1tbiKw5mI1QHNjuMMwAAsB_474:22dd:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 10119,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2019, 1, 26, 1, 58, 37),\n" +
                "    'receivedDate': new Date(2019, 1, 26, 1, 58, 38),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1-0310-20190225193903-0167091-8583241333>',\n" +
                "    'rootid': 'xtbBFBKvI1aDxuPUcgAAsc',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '187:1tbiuxhcI1PAAabAMAAAsY_1e90:7e94:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 943118,\n" +
                "    'from': '\"cleverbridge / Parallels International GmbH\" <no-reply@cleverbridge.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '参考号 158866052：您订阅的 Parallels Desktop for Mac Pro Edition（1 年） 款项已到期',\n" +
                "    'sentDate': new Date(2019, 1, 15, 10, 28, 47),\n" +
                "    'receivedDate': new Date(2019, 1, 15, 10, 29, 13),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'realAttached': true,\n" +
                "    'report': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1.3445e1099ebb016773f0@app10>',\n" +
                "    'rootid': ''\n" +
                "    }, {\n" +
                "    'id': '80:1tbiUAtcI1WBgMDohgAAse_1e8f:7e94:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 943117,\n" +
                "    'from': '\"cleverbridge / Parallels International GmbH\" <no-reply@cleverbridge.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '参考号 158866052：您订阅的 Parallels Desktop for Mac Pro Edition（1 年） 款项已到期',\n" +
                "    'sentDate': new Date(2019, 1, 15, 10, 27, 59),\n" +
                "    'receivedDate': new Date(2019, 1, 15, 10, 28, 28),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'realAttached': true,\n" +
                "    'report': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1.a34b6d30f1e3e06e54be@app15>',\n" +
                "    'rootid': ''\n" +
                "    }, {\n" +
                "    'id': '177:1tbisQNcI1Xlf42B7gAAsg_1e8f:7e94:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 943117,\n" +
                "    'from': '\"cleverbridge / Parallels International GmbH\" <no-reply@cleverbridge.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '参考号 158866052：您订阅的 Parallels Desktop for Mac Pro Edition（1 年） 款项已到期',\n" +
                "    'sentDate': new Date(2019, 1, 15, 10, 24, 30),\n" +
                "    'receivedDate': new Date(2019, 1, 15, 10, 25, 8),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'realAttached': true,\n" +
                "    'report': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1.76ec87406f693951814c@app13>',\n" +
                "    'rootid': ''\n" +
                "    }, {\n" +
                "    'id': '80:1tbiUBlKI1WBgCHh-wAAsL_471:2241:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 9958,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2019, 0, 28, 21, 9, 44),\n" +
                "    'receivedDate': new Date(2019, 0, 28, 21, 9, 47),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1-0310-20190125195941-9201503-6600992468>',\n" +
                "    'rootid': 'xtbBFBKvI1aDxuPUcgAAsc',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '176:1tbisAFCI1UMGuWTyQABst_6a4:b36:h:b:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 4570,\n" +
                "    'from': '\"荣丽娅\" <lkh1971@aminoacid-jirongpharm.com>',\n" +
                "    'to': '\"jane1229\\n\" <jane1229@163.com>',\n" +
                "    'subject': '系统答复：中信银行信用卡本地优惠速递荣丽娅，系统检测到您的信用良好，可在线申领银联标准高额卡',\n" +
                "    'sentDate': new Date(2019, 0, 21, 12, 19, 19),\n" +
                "    'receivedDate': new Date(2019, 0, 21, 12, 19, 24),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'hmid': '<2019012112192018622@aminoacid-jirongpharm.com>',\n" +
                "    'rootid': ''\n" +
                "    }, {\n" +
                "    'id': '80:1tbiUB06I1WBf0ufzgAAsl_19a8:7f99:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 781234,\n" +
                "    'from': '\"cleverbridge / Parallels International GmbH\" <no-reply@cleverbridge.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '参考号 158866052：您订阅的 Parallels Desktop for Mac Pro Edition（1 年） 款项已到期',\n" +
                "    'sentDate': new Date(2019, 0, 12, 19, 34, 40),\n" +
                "    'receivedDate': new Date(2019, 0, 12, 19, 34, 53),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'report': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1.72ded02685e566324ff8@app15>',\n" +
                "    'rootid': ''\n" +
                "    }, {\n" +
                "    'id': '43:1tbiKwgzI1QHM8pHXgAAsA_1e94:7ea3:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 943149,\n" +
                "    'from': '\"cleverbridge / Parallels International GmbH\" <no-reply@cleverbridge.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '参考号 158866052：您订阅的 Parallels Desktop for Mac Pro Edition（1 年） 款项已到期',\n" +
                "    'sentDate': new Date(2019, 0, 5, 19, 44, 31),\n" +
                "    'receivedDate': new Date(2019, 0, 5, 19, 45, 13),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'realAttached': true,\n" +
                "    'report': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1.3c29ccfb57d32b2edca0@app15>',\n" +
                "    'rootid': ''\n" +
                "    }, {\n" +
                "    'id': '45:1tbiLQ4yI1SIaGzEeQAAsX_81b:8e4:h:b:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 4351,\n" +
                "    'from': '\"我办中信银行信用卡贝志花\" <jsm5858@sgqp9999.com>',\n" +
                "    'to': '\"13429181586\\n\" <13429181586@163.com>',\n" +
                "    'subject': '【注册信息】Webservice forward mail jsm5858 to 13429181586',\n" +
                "    'sentDate': new Date(2019, 0, 4, 19, 10, 35),\n" +
                "    'receivedDate': new Date(2019, 0, 4, 19, 10, 39),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'accounts': ['13429181586@163.com'],\n" +
                "    'hmid': '<2019010419103514972@sgqp9999.com>',\n" +
                "    'rootid': ''\n" +
                "    }, {\n" +
                "    'id': '43:1tbiKx0oI1QHMx9jLAAAsN_476:2147:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 9714,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 11, 26, 1, 39, 40),\n" +
                "    'receivedDate': new Date(2018, 11, 26, 1, 39, 41),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1-0310-20181225223956-7391647-7600701803>',\n" +
                "    'rootid': 'xtbBFBKvI1aDxuPUcgAAsc',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '45:1tbiLRESI1SIZm5FhAAEsc_5e0:53b8:h:b:g',\n" +
                "    'fid': 3,\n" +
                "    'size': 22986,\n" +
                "    'from': '\"jane1229\" <jane1229@163.com>',\n" +
                "    'to': '2647138320@qq.com',\n" +
                "    'subject': 'Fw:浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 11, 3, 14, 43, 56),\n" +
                "    'receivedDate': new Date(2018, 11, 3, 14, 43, 56),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'rcptQueued': true,\n" +
                "    'rcptSucceed': true\n" +
                "    },\n" +
                "    'hmid': '<6f32edc7.9073.16772cf22ed.Coremail.jane1229@163.com>',\n" +
                "    'rootid': 'xtbBFBKvI1aDxuPUcgAAsc',\n" +
                "    'sndStatus': 3\n" +
                "    }, {\n" +
                "    'id': '177:1tbisR8LI1Xlez88MAAAs+_476:5196:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 22080,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 10, 27, 0, 10, 37),\n" +
                "    'receivedDate': new Date(2018, 10, 27, 0, 10, 39),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1-0310-20181125182420-7204794-9261749777>',\n" +
                "    'rootid': 'xtbBFBKvI1aDxuPUcgAAsc',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '44:1tbiLBH4I1spPHfqywAAs+_47d:b821:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 48336,\n" +
                "    'from': '\"平安信用卡\" <creditcard@service.pingan.com>',\n" +
                "    'to': 'JANE1229@163.COM',\n" +
                "    'subject': '平安信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 10, 8, 20, 35, 6),\n" +
                "    'receivedDate': new Date(2018, 10, 8, 20, 37, 5),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'hasTag': true\n" +
                "    },\n" +
                "    'hmid': 'paic.pecp_103141_3894964976',\n" +
                "    'rootid': '1tbisBGZI1UMEY2T4wAAsu',\n" +
                "    'tag': ['%st_p_bank%',\n" +
                "    '%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '276:xtbBFAf2I1aDypzyHQABsr_5c9:5ec:h:b:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 2997,\n" +
                "    'from': '\"中信银行信用卡申卡奖励汤仕权\" <fjjnwy520@57803.com>',\n" +
                "    'to': '\"jane1229\\n\" <jane1229@163.com>',\n" +
                "    'subject': 'Newsletter fjjnwy520 to jane1229',\n" +
                "    'sentDate': new Date(2018, 10, 6, 21, 34, 44),\n" +
                "    'receivedDate': new Date(2018, 10, 6, 21, 34, 48),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'hmid': '<2018110621344411145@57803.com>',\n" +
                "    'rootid': ''\n" +
                "    }, {\n" +
                "    'id': '187:1tbiuxDqI1O-+5ENPAAAsV_474:5217:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 22209,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 9, 25, 22, 15, 43),\n" +
                "    'receivedDate': new Date(2018, 9, 25, 22, 15, 44),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1-0310-20181025175653-6333959-3706834479>',\n" +
                "    'rootid': 'xtbBFBKvI1aDxuPUcgAAsc',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '419:xtbBowu4I1wAtN4oCQAAsk_76d:87b4:h:b:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 36641,\n" +
                "    'from': '\"service@intl.paypal.com\" <service@intl.paypal.com>',\n" +
                "    'to': '\"Guo Ying\" <jane1229@163.com>',\n" +
                "    'subject': '您给GENYMOBILE的付款收据',\n" +
                "    'sentDate': new Date(2018, 9, 6, 13, 17, 9),\n" +
                "    'receivedDate': new Date(2018, 9, 6, 13, 17, 14),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'emid': '1tbiKxrXI1QHLp46pQAAs5',\n" +
                "    'hmid': '<1538803029.5150@paypal.com>',\n" +
                "    'rootid': '1tbiKxrXI1QHLp46pQAAs5'\n" +
                "    }, {\n" +
                "    'id': '80:1tbiUBfWI1WBeaGh2wAAsE_762:7f62:h:b:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 34500,\n" +
                "    'from': '\"service@intl.paypal.com\" <service@intl.paypal.com>',\n" +
                "    'to': '\"Guo Ying\" <jane1229@163.com>',\n" +
                "    'subject': '请激活您的PayPal账户',\n" +
                "    'sentDate': new Date(2018, 9, 5, 12, 46, 43),\n" +
                "    'receivedDate': new Date(2018, 9, 5, 12, 46, 48),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'hasTag': true\n" +
                "    },\n" +
                "    'hmid': '<1538714803.8864@paypal.com>',\n" +
                "    'rootid': '',\n" +
                "    'tag': ['%st_p_reg%']\n" +
                "    }, {\n" +
                "    'id': '187:1tbiuxfMI1O-+geEjwAAsZ_471:5217:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 22205,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 8, 25, 15, 57, 39),\n" +
                "    'receivedDate': new Date(2018, 8, 25, 15, 57, 43),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1-0310-20180925154429-6735055-8866334436>',\n" +
                "    'rootid': 'xtbBFBKvI1aDxuPUcgAAsc',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '43:1tbiKx29I1QHLTtiLAAAsj_838:750:h:b:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 4014,\n" +
                "    'from': '\"13429181586\" <13429181586@hongshunzuche.com>',\n" +
                "    'to': '\"13429181586\\n\" <13429181586@163.com>',\n" +
                "    'subject': '交通银行信用卡最红星期五超市活动，指定合作超市门店内刷卡可获得5%刷卡金奖励，白金信用卡更可享10%刷卡金奖励贾台定',\n" +
                "    'sentDate': new Date(2018, 8, 10, 11, 12, 7),\n" +
                "    'receivedDate': new Date(2018, 8, 10, 11, 12, 12),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'accounts': ['13429181586@163.com'],\n" +
                "    'hmid': '<201809101112077729@hongshunzuche.com>',\n" +
                "    'rootid': ''\n" +
                "    }, {\n" +
                "    'id': '45:1tbiLRm5I1SIYcCyDQAAss_477:15aa5:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 89934,\n" +
                "    'from': '\"平安信用卡\" <creditcard@service.pingan.com>',\n" +
                "    'to': 'JANE1229@163.COM',\n" +
                "    'subject': '平安信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 8, 6, 22, 11, 31),\n" +
                "    'receivedDate': new Date(2018, 8, 6, 22, 11, 37),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'hasTag': true\n" +
                "    },\n" +
                "    'hmid': 'paic.pecp_103141_3778564011',\n" +
                "    'rootid': '1tbisBGZI1UMEY2T4wAAsu',\n" +
                "    'tag': ['%st_p_bank%',\n" +
                "    '%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '276:xtbBFBKvI1aDxuPUcgAAsc_470:51aa:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 22094,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 7, 27, 15, 41, 9),\n" +
                "    'receivedDate': new Date(2018, 7, 27, 16, 9, 54),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1-0310-20180825141218-7465142-0789608485>',\n" +
                "    'rootid': '',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '419:xtbBoxSnI1wAsi6Y4wAAsI_47c:24a6d:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 151324,\n" +
                "    'from': '\"平安信用卡\" <creditcard@service.pingan.com>',\n" +
                "    'to': 'JANE1229@163.COM',\n" +
                "    'subject': '平安信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 7, 6, 6, 26, 54),\n" +
                "    'receivedDate': new Date(2018, 7, 6, 6, 26, 58),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'hasTag': true\n" +
                "    },\n" +
                "    'emid': '1tbisBGZI1UMEY2T4wAAsu',\n" +
                "    'hmid': 'paic.pecp_30231_3707363526',\n" +
                "    'rootid': '1tbisBGZI1UMEY2T4wAAsu',\n" +
                "    'tag': ['%st_p_bank%',\n" +
                "    '%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '352:xtbBYBQ9I1r75-3xPAAAsc_472:523b:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 22242,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 6, 25, 17, 57, 27),\n" +
                "    'receivedDate': new Date(2018, 6, 25, 17, 57, 28),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'emid': '1tbiUAiOI1WBdrJc+gAAsD',\n" +
                "    'hmid': '<1-0310-20180725133357-7920306-7965866467>',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '126:1tbifgGgI1r6mFwXKQAAsm_725:1e970:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 127173,\n" +
                "    'from': '\"平安信用卡\" <creditcard@service.pingan.com>',\n" +
                "    'to': 'JANE1229@163.COM',\n" +
                "    'subject': '平安信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 6, 6, 0, 23, 15),\n" +
                "    'receivedDate': new Date(2018, 6, 6, 0, 23, 18),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'hasTag': true\n" +
                "    },\n" +
                "    'emid': '1tbisBB6I1UMD9fGTQABsy',\n" +
                "    'hmid': 'paic.pecp_30231_3634656221',\n" +
                "    'tag': ['%st_p_bank%',\n" +
                "    '%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '176:1tbisB1wI1UMDzaqEQABsk_472:523b:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 22243,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 5, 25, 23, 32, 28),\n" +
                "    'receivedDate': new Date(2018, 5, 25, 23, 32, 30),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'forwarded': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1-0310-20180625134553-8645512-0487770769>',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '45:1tbiLRldI1SIXIrMdgAAs6_47c:1bca9:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 115032,\n" +
                "    'from': '\"平安信用卡\" <creditcard@service.pingan.com>',\n" +
                "    'to': 'JANE1229@163.COM',\n" +
                "    'subject': '平安信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 5, 6, 9, 35, 52),\n" +
                "    'receivedDate': new Date(2018, 5, 6, 9, 35, 54),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'hasTag': true\n" +
                "    },\n" +
                "    'hmid': 'paic.pecp_30231_3565000399',\n" +
                "    'tag': ['%st_p_bank%',\n" +
                "    '%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '187:1tbiuwdKI1O-8qzE0gAAsx_994:12b5:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 7290,\n" +
                "    'from': '\"Amazon.cn\" <account-update@amazon.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '亚马逊密码帮助',\n" +
                "    'sentDate': new Date(2018, 4, 18, 14, 18, 44),\n" +
                "    'receivedDate': new Date(2018, 4, 18, 14, 18, 47),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'hmid': '<0101016371e68c64-eeb15bd0-993d-4142-a00c-56fc77ee3fb4-000000@us-west-2.amazonses.com>'\n" +
                "    }, {\n" +
                "    'id': '45:1tbiLQo9I1SIWrBu+wAAsa_47e:16a85:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 94007,\n" +
                "    'from': '\"平安信用卡\" <creditcard@service.pingan.com>',\n" +
                "    'to': 'JANE1229@163.COM',\n" +
                "    'subject': '平安信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 4, 6, 7, 0, 24),\n" +
                "    'receivedDate': new Date(2018, 4, 6, 7, 0, 26),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'hasTag': true\n" +
                "    },\n" +
                "    'hmid': 'paic.pecp_30231_3486285057',\n" +
                "    'tag': ['%st_p_bank%',\n" +
                "    '%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '419:xtbBowUEI1wAt0BWtAAAsI_24de:8ea0:h:b:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 45999,\n" +
                "    'from': '\"淘宝网\" <bt463827763@qq.com>',\n" +
                "    'to': '\"jane1229\" <jane1229@163.com>',\n" +
                "    'subject': '转发：交通银行信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 2, 12, 10, 50, 53),\n" +
                "    'receivedDate': new Date(2018, 2, 12, 10, 50, 56),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'emid': 'xtbBFBAHI1aDvWeRggAAs8',\n" +
                "    'hmid': '<tencent_1D0A1B1F7E0E5C05CBA1466361423076770A@qq.com>'\n" +
                "    }, {\n" +
                "    'id': '276:xtbBFATZI1aDu4K8HAABsn_471:5194:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 22073,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2018, 0, 27, 6, 5, 44),\n" +
                "    'receivedDate': new Date(2018, 0, 27, 6, 5, 46),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1-0310-20180125162446-3445479-3073197441>',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '330:xtbBShRHI1r76pRj1wAAsR_1eb4:7046:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 227330,\n" +
                "    'from': '\"cleverbridge / Parallels International GmbH\" <no-reply@cleverbridge.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '参考号 126866480：您订阅的 Parallels Desktop for Mac Pro Edition（1 年） 款项已到期',\n" +
                "    'sentDate': new Date(2018, 0, 12, 19, 20, 41),\n" +
                "    'receivedDate': new Date(2018, 0, 12, 19, 20, 49),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'realAttached': true,\n" +
                "    'report': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'emid': '1tbiKxXLI1QHIGSsfAAAsR',\n" +
                "    'hmid': '<1.3ba31963110deb1243ed@app16>'\n" +
                "    }, {\n" +
                "    'id': '330:xtbBShBHI1r76pltqwAAsq_19ba:713b:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 65700,\n" +
                "    'from': '\"cleverbridge / Parallels International GmbH\" <no-reply@cleverbridge.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '参考号 126866480：您订阅的 Parallels Desktop for Mac Pro Edition（1 年） 款项已到期',\n" +
                "    'sentDate': new Date(2018, 0, 10, 11, 8, 26),\n" +
                "    'receivedDate': new Date(2018, 0, 10, 11, 8, 33),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'report': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'emid': '1tbiKxHJI1QHIDleNQAAsx',\n" +
                "    'hmid': '<1.10da33ca9277ab9781ee@app15>'\n" +
                "    }, {\n" +
                "    'id': '182:1tbitgEUI1r7r3TC4gAAsa_1eb3:7045:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 227328,\n" +
                "    'from': '\"cleverbridge / Parallels International GmbH\" <no-reply@cleverbridge.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '参考号 126866480：您订阅的 Parallels Desktop for Mac Pro Edition（1 年） 款项已到期',\n" +
                "    'sentDate': new Date(2018, 0, 3, 11, 17, 52),\n" +
                "    'receivedDate': new Date(2018, 0, 3, 11, 17, 56),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'realAttached': true,\n" +
                "    'report': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'emid': '1tbiLQTCI1SIVGosIQAAsn',\n" +
                "    'hmid': '<1.b964ee8ade14c0726ea0@app12>'\n" +
                "    }, {\n" +
                "    'id': '80:1tbiUB25I1WBazfQaQABsn_46f:5182:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 22054,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2017, 11, 26, 7, 4, 59),\n" +
                "    'receivedDate': new Date(2017, 11, 26, 7, 5, 0),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1-0310-20171225191134-7609990-6552898935>',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '44:1tbiLBKdI1XlmUeDEgABs+_46f:5182:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 22055,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2017, 10, 28, 13, 45, 15),\n" +
                "    'receivedDate': new Date(2017, 10, 28, 13, 45, 18),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'hmid': '<1-0310-20171128123937-3394437-5974680787>',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '622:xtbCbhEAACHe-20058oYcT_470:5182:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 22054,\n" +
                "    'from': '\"浦发银行信用卡中心\" <estmtservice@eb.spdbccc.com.cn>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '浦发银行-信用卡电子账单',\n" +
                "    'sentDate': new Date(2017, 10, 27, 21, 54, 4),\n" +
                "    'receivedDate': new Date(2017, 10, 27, 21, 54, 6),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'hasTag': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'emid': '1tbiux6dI1O-6bOkBAAAsN',\n" +
                "    'hmid': '<1-0310-20171125134614-1602414-9515463475>',\n" +
                "    'tag': ['%st_p_bill%',\n" +
                "    '%st_p_extr%',\n" +
                "    '%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '80:1tbiUB3xI1WBYcg5swAAso_606:189d:h:8:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 7843,\n" +
                "    'from': '\"淘宝网\" <service@mc.mail.taobao.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '亲爱的：daisy_huahua这是一封让你感觉错过上亿的邮件！！！',\n" +
                "    'sentDate': new Date(2017, 5, 9, 23, 30, 5),\n" +
                "    'receivedDate': new Date(2017, 5, 9, 23, 30, 5),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'hasTag': true\n" +
                "    },\n" +
                "    'ctrls': {\n" +
                "    'RulesType': '7110_taobaocom'\n" +
                "    },\n" +
                "    'hmid': '<988849495.4501497022205165.JavaMail.admin@messagecenter011230138074.eu13>',\n" +
                "    'tag': ['%st_p_trans%']\n" +
                "    }, {\n" +
                "    'id': '328:xtbBSAsVI1r75VVTDQAAs6_18fa:7dad:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 210699,\n" +
                "    'from': '\"cleverbridge / Parallels International GmbH\" <no-reply@cleverbridge.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '参考号 105070137：Parallels Desktop for Mac Pro Edition（1 年） 的付款信息',\n" +
                "    'sentDate': new Date(2017, 0, 10, 11, 0, 14),\n" +
                "    'receivedDate': new Date(2017, 0, 10, 11, 0, 31),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true,\n" +
                "    'realAttached': true,\n" +
                "    'report': true,\n" +
                "    'inlineAttached': true,\n" +
                "    'attached': true\n" +
                "    },\n" +
                "    'emid': '1tbiLA5bI1XlipB69QAAs-',\n" +
                "    'hmid': '<1.88981dee1eaba0014d50@app12>'\n" +
                "    }, {\n" +
                "    'id': '470:xtbB1gIAAB-SWldqhPQtcj_1493:38be:h:b:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 19834,\n" +
                "    'from': '\"service@intl.paypal.com\" <service@intl.paypal.com>',\n" +
                "    'to': '\"郭 颖\" <jane1229@163.com>',\n" +
                "    'subject': '您付给Ahmad Qureshi的款项',\n" +
                "    'sentDate': new Date(2016, 3, 15, 12, 33, 18),\n" +
                "    'receivedDate': new Date(2016, 3, 15, 12, 33, 26),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'emid': '1tbiKxZMI1QHARjrHwAAss',\n" +
                "    'hmid': '<1460694798.14947@paypal.com>'\n" +
                "    }, {\n" +
                "    'id': '493:xtbB7RcAACEjQvhD0OvWcs_4e4:1754:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 7271,\n" +
                "    'from': '\"拉勾网\" <service@email.lagou.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '51信用卡管家-无线测试工程师招聘反馈通知：被转发',\n" +
                "    'sentDate': new Date(2014, 10, 12, 15, 49, 9),\n" +
                "    'receivedDate': new Date(2014, 10, 12, 15, 49, 14),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'emid': '1tbisBlCI1EAK4B2ywAAsP',\n" +
                "    'hmid': '<1580403301.900259.1415778497793.JavaMail.EASEYE$@easeye>'\n" +
                "    }, {\n" +
                "    'id': '262:xtbBBgz3I1r74-RZyQAAs4_83f:1794:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 8198,\n" +
                "    'from': '\"支付宝\" <alipay@mail.alipay.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '充值退回成功提醒',\n" +
                "    'sentDate': new Date(2014, 6, 19, 16, 17, 4),\n" +
                "    'receivedDate': new Date(2014, 6, 19, 16, 17, 5),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'popRead': true,\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'ctrls': {\n" +
                "    'RulesType': '7190_alipaycom'\n" +
                "    },\n" +
                "    'emid': '1tbiuwjNI1O-pm-McwABsG',\n" +
                "    'hmid': '<1409592521.22453841405757824671.JavaMail.admin@gotone-70-55>',\n" +
                "    'rootid': ''\n" +
                "    }, {\n" +
                "    'id': '216:1tbi2BUAABeXiM1Xmj10cM_59e:1c46:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 8725,\n" +
                "    'from': '\"支付宝\" <alipay@mail.alipay.com>',\n" +
                "    'to': '\"jane1229@163.com\" <jane1229@163.com>',\n" +
                "    'subject': '您的支付宝账户支付密码已被重置，请立即登录并更换新密码！',\n" +
                "    'sentDate': new Date(2011, 9, 25, 12, 57, 2),\n" +
                "    'receivedDate': new Date(2011, 9, 25, 12, 57, 8),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'ctrls': {\n" +
                "    'RulesType': '5010_alipay'\n" +
                "    },\n" +
                "    'emid': '1tbiKxDjI0jfVe706wAAsI',\n" +
                "    'hmid': '<27767437.1319518622065.JavaMail.admin@gotone-30-5>'\n" +
                "    }, {\n" +
                "    'id': '134:1tbihh4AABcc739UTlnScS_640:1e7c:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 9456,\n" +
                "    'from': '\"支付宝\" <alipay@mail.alipay.com>',\n" +
                "    'to': '\"jane1229@163.com\" <jane1229@163.com>',\n" +
                "    'subject': '支付密码重新设定！',\n" +
                "    'sentDate': new Date(2011, 9, 25, 12, 54, 41),\n" +
                "    'receivedDate': new Date(2011, 9, 25, 12, 54, 44),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'ctrls': {\n" +
                "    'RulesType': '5010_alipay'\n" +
                "    },\n" +
                "    'emid': '1tbisRTjI00vKA60ugAAsv',\n" +
                "    'hmid': '<4324393.1319518481839.JavaMail.admin@gotone-30-11>'\n" +
                "    }, {\n" +
                "    'id': '164:1tbipBIAABb9PEVH9yDrcf_5bf:1511:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 6914,\n" +
                "    'from': '\"支付宝\" <alipay@mail.alipay.com>',\n" +
                "    'to': '\"jane1229@163.com\" <jane1229@163.com>',\n" +
                "    'subject': '信用卡还款成功提醒',\n" +
                "    'sentDate': new Date(2011, 8, 22, 17, 1, 23),\n" +
                "    'receivedDate': new Date(2011, 8, 22, 17, 1, 22),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'ctrls': {\n" +
                "    'RulesType': '5010_alipay'\n" +
                "    },\n" +
                "    'emid': '1tbiUBTCI0iZajuXTQAAs-',\n" +
                "    'hmid': '<381155.1316682083204.JavaMail.admin@gotone-30-10>'\n" +
                "    }, {\n" +
                "    'id': '138:1tbiihsAABgYBToHhc8rcI_5d8:19c1:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 8139,\n" +
                "    'from': '\"支付宝\" <alipay@mail.alipay.com>',\n" +
                "    'to': '\"jane1229@163.com\" <jane1229@163.com>',\n" +
                "    'subject': '您的代付申请状态已改变，请立即查看!',\n" +
                "    'sentDate': new Date(2011, 8, 14, 22, 26, 42),\n" +
                "    'receivedDate': new Date(2011, 8, 14, 22, 26, 40),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'ctrls': {\n" +
                "    'RulesType': '5010_alipay'\n" +
                "    },\n" +
                "    'emid': '1tbisB26I0kZ3eUyJwAAsJ',\n" +
                "    'hmid': '<29770087.1316010402053.JavaMail.admin@gotone-30-1>'\n" +
                "    }, {\n" +
                "    'id': '139:1tbiix4AABcc7ZNMXIM0cH_5d3:198a:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 8080,\n" +
                "    'from': '\"支付宝\" <alipay@mail.alipay.com>',\n" +
                "    'to': '\"jane1229@163.com\" <jane1229@163.com>',\n" +
                "    'subject': '您申请了一笔代付，请立即核实!',\n" +
                "    'sentDate': new Date(2011, 8, 14, 22, 26, 14),\n" +
                "    'receivedDate': new Date(2011, 8, 14, 22, 26, 12),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'ctrls': {\n" +
                "    'RulesType': '5010_alipay'\n" +
                "    },\n" +
                "    'emid': '1tbiLRa6I0qSvxG1ngAAs-',\n" +
                "    'hmid': '<22809542.1316010374319.JavaMail.admin@gotone-30-11>'\n" +
                "    }, {\n" +
                "    'id': '634:xtbCehBMI1pm6TOhfQAAs-_546:14fe:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 6775,\n" +
                "    'from': '\"支付宝\" <alipay@mail.alipay.com>',\n" +
                "    'to': '\"jane1229@163.com\" <jane1229@163.com>',\n" +
                "    'subject': '信用卡还款成功提醒',\n" +
                "    'sentDate': new Date(2011, 8, 5, 20, 25, 19),\n" +
                "    'receivedDate': new Date(2011, 8, 5, 20, 25, 20),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'ctrls': {\n" +
                "    'RulesType': '5010_alipay'\n" +
                "    },\n" +
                "    'emid': '1tbiuwuxI0X0LyczwwAAsr',\n" +
                "    'hmid': '<22495202.1315225519441.JavaMail.admin@gotone-30-12>'\n" +
                "    }, {\n" +
                "    'id': '75:1tbiSxUAABbGa2xjxy28c8_5c2:19f2:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 8166,\n" +
                "    'from': '\"支付宝\" <alipay@mail.alipay.com>',\n" +
                "    'to': '\"jane1229@163.com\" <jane1229@163.com>',\n" +
                "    'subject': '代充信息通知邮件',\n" +
                "    'sentDate': new Date(2011, 7, 22, 13, 11, 16),\n" +
                "    'receivedDate': new Date(2011, 7, 22, 13, 11, 16),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'ctrls': {\n" +
                "    'RulesType': '5010_alipay'\n" +
                "    },\n" +
                "    'emid': '1tbiUBujI0iZaSVe0QAAsZ',\n" +
                "    'hmid': '<27637737.1313989876673.JavaMail.admin@gotone-30-3>'\n" +
                "    }, {\n" +
                "    'id': '622:xtbCbgwAACHe-2oT60u0cP_5bf:14fe:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 6896,\n" +
                "    'from': '\"支付宝\" <alipay@mail.alipay.com>',\n" +
                "    'to': '\"jane1229@163.com\" <jane1229@163.com>',\n" +
                "    'subject': '信用卡还款成功提醒',\n" +
                "    'sentDate': new Date(2011, 7, 16, 13, 51, 1),\n" +
                "    'receivedDate': new Date(2011, 7, 16, 13, 51, 11),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'ctrls': {\n" +
                "    'RulesType': '5010_alipay'\n" +
                "    },\n" +
                "    'emid': '1tbiuxSdI0X0Lmkd4AABsb',\n" +
                "    'hmid': '<4030127.1313473861255.JavaMail.admin@gotone-30-8>'\n" +
                "    }, {\n" +
                "    'id': '213:1tbi1QUAABcmCOgSKutOcc_66d:202b:h:q:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 9930,\n" +
                "    'from': '\"支付宝提醒\" <service@mail.alipay.com>',\n" +
                "    'to': '\"jane1229@163.com\" <jane1229@163.com>',\n" +
                "    'subject': '太棒了！有了TA，和信用卡滞纳金说拜拜吧。',\n" +
                "    'sentDate': new Date(2011, 7, 12, 10, 13, 16),\n" +
                "    'receivedDate': new Date(2011, 7, 12, 10, 13, 16),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'ctrls': {\n" +
                "    'RulesType': '5010_alipay'\n" +
                "    },\n" +
                "    'emid': '1tbiLBeZI0kikLMhBAAAss',\n" +
                "    'hmid': '<26100677.1313115196691.JavaMail.admin@gotone-30-10>'\n" +
                "    }, {\n" +
                "    'id': '437:xtbBtRIAABPbEABaLgkDcB_8a0:3cc8:h:b:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 17768,\n" +
                "    'from': '\"支付宝\" <service@mail.alipay.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '亲爱的郭颖，祝您生日快乐！支付宝送给您20元生日礼券！',\n" +
                "    'sentDate': new Date(2009, 10, 21, 19, 31, 18),\n" +
                "    'receivedDate': new Date(2009, 10, 21, 19, 31, 16),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'ctrls': {\n" +
                "    'RulesType': '5010_alipay;ntesfake'\n" +
                "    },\n" +
                "    'emid': '1tbiLAkiI0kihR8srAAAsV',\n" +
                "    'rootid': ''\n" +
                "    }, {\n" +
                "    'id': '75:1tbiSwAAABbGbBErOn-KcC_86f:3343:h:8:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 15282,\n" +
                "    'from': '\"淘宝网\" <service@smtp.mail.taobao.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '（AD）亲爱的daisy_huahua，上淘宝购物，现在可以去楼下的便利店付款啦!',\n" +
                "    'sentDate': new Date(2009, 7, 11, 10, 43, 26),\n" +
                "    'receivedDate': new Date(2009, 7, 11, 10, 51, 12),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'popRead': true,\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'ctrls': {\n" +
                "    'RulesType': '5120_taobao'\n" +
                "    },\n" +
                "    'emid': '1tbiKwe7I0jfQ0coxwAAsI'\n" +
                "    }, {\n" +
                "    'id': '403:xtbBkwW6I0ojeMVekAAAsV_8a3:3343:h:8:g',\n" +
                "    'fid': 1,\n" +
                "    'size': 15334,\n" +
                "    'from': '\"淘宝网\" <service@smtp.mail.taobao.com>',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': '（AD）亲爱的${NICK}，上淘宝购物，现在可以去楼下的便利店付款啦！',\n" +
                "    'sentDate': new Date(2009, 7, 10, 13, 41, 47),\n" +
                "    'receivedDate': new Date(2009, 7, 10, 13, 52, 32),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'popRead': true,\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'ctrls': {\n" +
                "    'RulesType': '5120_taobao'\n" +
                "    },\n" +
                "    'rootid': ''\n" +
                "    }, {\n" +
                "    'id': '419:xtbBowEAABSbiDkUGqG7cI_8f4:4024:h:q:8',\n" +
                "    'fid': 1,\n" +
                "    'size': 25658,\n" +
                "    'from': 'symantec.cn@digitalriver.com',\n" +
                "    'to': 'jane1229@163.com',\n" +
                "    'subject': 'CN Symantec – 订单 # 8048956613 的订单确认',\n" +
                "    'sentDate': new Date(2009, 3, 28, 10, 50, 15),\n" +
                "    'receivedDate': new Date(2009, 3, 28, 10, 50, 17),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'novirus',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'popRead': true,\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'emid': '1tbiLQhSI0CeEcyNLAAAs2',\n" +
                "    'rootid': ''\n" +
                "    }, {\n" +
                "    'id': '267:1tbixg-LI0PUiUhFKgAAsY_d9c:273e:h:q:g',\n" +
                "    'fid': 3,\n" +
                "    'size': 13578,\n" +
                "    'from': 'jane1229@163.com',\n" +
                "    'to': 'symantec.cn@digitalriver.com',\n" +
                "    'subject': 'Re:CN_Symantec_–_订单_#_5352940213_的订单确认',\n" +
                "    'sentDate': new Date(2008, 3, 4, 14, 18, 40),\n" +
                "    'receivedDate': new Date(2008, 3, 4, 14, 18, 41),\n" +
                "    'priority': 3,\n" +
                "    'backgroundColor': 0,\n" +
                "    'antiVirusStatus': 'unscaned',\n" +
                "    'label0': 0,\n" +
                "    'flags': {\n" +
                "    'read': true\n" +
                "    },\n" +
                "    'rootid': ''\n" +
                "    }],\n" +
                "    'keywords': ['信用',\n" +
                "    '卡'],\n" +
                "    'groupings': {\n" +
                "    'fromAddress': [{\n" +
                "    'val': '13429181586@hongshunzuche.com',\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': 'account-update@amazon.cn',\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': 'alipay@mail.alipay.com',\n" +
                "    'cnt': 9\n" +
                "    }, {\n" +
                "    'val': 'bt463827763@qq.com',\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': 'creditcard@service.pingan.com',\n" +
                "    'cnt': 6\n" +
                "    }, {\n" +
                "    'val': 'estmtservice@eb.spdbccc.com.cn',\n" +
                "    'cnt': 19\n" +
                "    }, {\n" +
                "    'val': 'fjjnwy520@57803.com',\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': 'hairswarm@318pfb.cn',\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': 'jane1229@163.com',\n" +
                "    'cnt': 2\n" +
                "    }, {\n" +
                "    'val': 'jsm5858@sgqp9999.com',\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': 'lkh1971@aminoacid-jirongpharm.com',\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': 'no-reply@cleverbridge.com',\n" +
                "    'cnt': 10\n" +
                "    }, {\n" +
                "    'val': 'paypal@mail.paypal.com',\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': 'service@email.lagou.com',\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': 'service@intl.paypal.com',\n" +
                "    'cnt': 3\n" +
                "    }, {\n" +
                "    'val': 'service@mail.alipay.com',\n" +
                "    'cnt': 2\n" +
                "    }, {\n" +
                "    'val': 'service@mc.mail.taobao.com',\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': 'service@smtp.mail.taobao.com',\n" +
                "    'cnt': 2\n" +
                "    }, {\n" +
                "    'val': 'symantec.cn@digitalriver.com',\n" +
                "    'cnt': 1\n" +
                "    }],\n" +
                "    'fid': [{\n" +
                "    'val': 1,\n" +
                "    'cnt': 61\n" +
                "    }, {\n" +
                "    'val': 3,\n" +
                "    'cnt': 2\n" +
                "    }, {\n" +
                "    'val': 18,\n" +
                "    'cnt': 1\n" +
                "    }],\n" +
                "    'sentDate': [{\n" +
                "    'val': new Date(2008, 3, 4),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2009, 3, 28),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2009, 7, 10),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2009, 7, 11),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2009, 10, 21),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2011, 7, 12),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2011, 7, 16),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2011, 7, 22),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2011, 8, 5),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2011, 8, 14),\n" +
                "    'cnt': 2\n" +
                "    }, {\n" +
                "    'val': new Date(2011, 8, 22),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2011, 9, 25),\n" +
                "    'cnt': 2\n" +
                "    }, {\n" +
                "    'val': new Date(2014, 6, 19),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2014, 10, 12),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2016, 3, 15),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2017, 0, 10),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2017, 5, 9),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2017, 10, 27),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2017, 10, 28),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2017, 11, 26),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 0, 3),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 0, 10),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 0, 12),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 0, 27),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 2, 12),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 4, 6),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 4, 18),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 5, 6),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 5, 25),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 6, 6),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 6, 25),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 7, 6),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 7, 27),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 8, 6),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 8, 10),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 8, 25),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 9, 5),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 9, 6),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 9, 25),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 10, 6),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 10, 8),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 10, 27),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 11, 3),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2018, 11, 26),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2019, 0, 4),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2019, 0, 5),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2019, 0, 12),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2019, 0, 21),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2019, 0, 28),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2019, 1, 15),\n" +
                "    'cnt': 3\n" +
                "    }, {\n" +
                "    'val': new Date(2019, 1, 26),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2019, 2, 2),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2019, 3, 26),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2019, 4, 26),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2019, 5, 1),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2019, 5, 25),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2019, 6, 25),\n" +
                "    'cnt': 1\n" +
                "    }, {\n" +
                "    'val': new Date(2019, 8, 30),\n" +
                "    'cnt': 2\n" +
                "    }, {\n" +
                "    'val': new Date(2019, 9, 8),\n" +
                "    'cnt': 1\n" +
                "    }],\n" +
                "    'flags.attached': [{\n" +
                "    'val': false,\n" +
                "    'cnt': 56\n" +
                "    }, {\n" +
                "    'val': true,\n" +
                "    'cnt': 8\n" +
                "    }],\n" +
                "    'flags.read': [{\n" +
                "    'val': true,\n" +
                "    'cnt': 64\n" +
                "    }]\n" +
                "    }\n" +
                "    }";
        System.out.println(data.replaceAll("\"", "").replaceAll("\'", "\""));
    }

    public static long trailingZeros(long n) {
        // write your code here, try to do it without arithmetic operators.
        BigInteger x = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            x = x.multiply(BigInteger.valueOf(i));
        }
        int n0 = 0;
        int m = 10;
        for (int i = 1; i <= 50000; i++) {
            BigInteger t = mi(m, i);
            BigInteger remainder = x.remainder(t);
            if (!remainder.equals(BigInteger.ZERO)) {
                break;
            } else {
                n0++;
            }
        }
        return n0;
    }

    private static BigInteger mi(int x, int n) {
        BigInteger t = BigInteger.valueOf(x);
        if (n == 0) {
            return BigInteger.ZERO;
        }
        for (int i = 0; i < n - 1; i++) {
            t = t.multiply(BigInteger.valueOf(x));
        }
        return t;
    }

}
