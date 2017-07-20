package com.bank.creditcardcollection.constant.information;

import android.util.SparseArray;

import java.util.HashMap;

/**
 * Created by liyanjun on 2017/7/20.
 */
public final class Information {
    private Information() {
        //1、是否选项
        isOption = new HashMap<>(2);
        isOption.put("Y", "是");
        isOption.put("N", "否");
        //2、性别
        gender = new SparseArray<>(2);
        gender.append(0, "女");
        gender.append(1, "男");
        //3、证件类型
        credentialType = new SparseArray<>(4);
        credentialType.append(1, "身份证");
        credentialType.append(2, "外国护照");
        credentialType.append(3, "港澳通行证");
        credentialType.append(4, "其他");
        //4、证件有效期
        credentialValidTime = new SparseArray<>(2);
        credentialValidTime.append(1, "短期");
        credentialValidTime.append(2, "长期");
        //5、婚姻状况
        marriageState = new SparseArray<>(6);
        marriageState.append(1, "未婚");
        marriageState.append(2, "已婚子女");
        marriageState.append(3, "已婚无子女");
        marriageState.append(4, "离异");
        marriageState.append(5, "丧偶");
        marriageState.append(9, "其他");
        //6、教育程度
        education = new SparseArray<>(6);
        education.append(1, "博士");
        education.append(2, "硕士");
        education.append(3, "本科");
        education.append(4, "大专");
        education.append(5, "高中及中专");
        education.append(6, "初中以下");
        //7、直系亲属与申请人关系
        kinsfolkRelation = new SparseArray<>(4);
        kinsfolkRelation.append(1, "配偶");
        kinsfolkRelation.append(2, "子女");
        kinsfolkRelation.append(3, "父母");
        kinsfolkRelation.append(4, "兄弟姐妹");
        //8、紧急联系人与申请人关系
        instancyRelation = new SparseArray<>(4);
        instancyRelation.append(1, "配偶");
        instancyRelation.append(2, "亲属");
        instancyRelation.append(3, "朋友");
        instancyRelation.append(4, "同事");
        //9、行业性质
        industryType = new SparseArray<>(7);
        industryType.append(1, "机关-事业单位");
        industryType.append(2, "国有企业");
        industryType.append(3, "独资企业");
        industryType.append(4, "合资-合作单位");
        industryType.append(5, "股份制企业");
        industryType.append(6, "私人企业");
        industryType.append(9, "其他");
        //10、职务分类
        dutyType = new SparseArray<>(5);
        dutyType.append(1, "负责人/法人");
        dutyType.append(2, "高层管理人员");
        dutyType.append(3, "中层管理人员");
        dutyType.append(4, "正式员工");
        dutyType.append(5, "非正式员工");
        //11、房产信息
        houseInformation = new SparseArray<>(6);
        houseInformation.append(1, "自有住房");
        houseInformation.append(2, "贷款购房");
        houseInformation.append(3, "与亲属合住");
        houseInformation.append(4, "租房");
        houseInformation.append(5, "集体宿舍");
        houseInformation.append(9, "其他");
        //12、邮寄地址类型
        mailAddress = new SparseArray<>(2);
        mailAddress.append(1, "住宅地址");
        mailAddress.append(2, "单位地址");
        //13、约定还款方式
        repayType = new SparseArray<>(2);
        repayType.append(1, "全额还款");
        repayType.append(2, "最低还款额");
        //14、推荐人和申请人关系
        recommendType = new SparseArray<>(5);
        recommendType.append(1, "亲戚");
        recommendType.append(2, "朋友");
        recommendType.append(3, "客户");
        recommendType.append(4, "同学");
        recommendType.append(9, "其他");
        //15、已核实内容
        proveDetails = new SparseArray<>(4);
        proveDetails.append(1, "亲见申请人签名及身份证原件");
        proveDetails.append(2, "确认申请人家庭状况");
        proveDetails.append(3, "亲访申请人单位并确认工作");
        proveDetails.append(4, "合影");
        //16、申请来源
        sourceType = new SparseArray<>(4);
        sourceType.append(1, "行员推荐");
        sourceType.append(2, "熟人介绍");
        sourceType.append(3, "柜台拜访");
        sourceType.append(4, "陌拜推广");
        //17、申请人与我行来往方式
        dealingsType = new SparseArray<>(6);
        dealingsType.append(1, "理财客户");
        dealingsType.append(2, "代发工资户");
        dealingsType.append(3, "个人存款户");
        dealingsType.append(4, "个人贷款户");
        dealingsType.append(5, "对公单位员工");
        dealingsType.append(9, "其他");
    }

    private static final class SingleTon {
         static final Information INSTANCE = new Information();
    }

    /**
     * 获取单例
     *
     * @return
     */
    static Information getInstance() {
        return SingleTon.INSTANCE;
    }

    //1、是否选项: Y:是 , N:否
    private final HashMap<String, String> isOption;
    //2、性别: 0：女 , 1:男
    private final SparseArray<String> gender;
    //3、证件类型: 1：身份证 , 2:外国护照 , 3:港澳通行证 , 9:其他
    private final SparseArray<String> credentialType;
    //4、证件有效期: 1：短期 , 2:长期
    private final SparseArray<String> credentialValidTime;
    //5、婚姻状况: 1：未婚 , 2:已婚子女 , 3:已婚无子女 , 4: 离异 , 5:丧偶 , 9:其他
    private final SparseArray<String> marriageState;
    //6、教育程度: 1：博士 , 2:硕士 , 3:本科 , 4: 大专 , 5:高中及中专 , 6:初中以下
    private final SparseArray<String> education;
    //7、直系亲属与申请人关系: 1：配偶 , 2:子女 , 3:父母 , 4: 兄弟姐妹
    private final SparseArray<String> kinsfolkRelation;
    //8、紧急联系人与申请人关系: 1：配偶 , 2:亲属 , 3:朋友 , 4: 同事
    private final SparseArray<String> instancyRelation;
    //9、行业性质: 1：机关-事业单位 , 2:国有企业 , 3:独资企业 , 4: 合资-合作单位 , 5:股份制企业 , 6:私人企业 , 9:其他
    private final SparseArray<String> industryType;
    //10、职务分类: 1：负责人/法人 , 2:高层管理人员 , 3:中层管理人员 , 4: 正式员工 , 5:非正式员工
    private final SparseArray<String> dutyType;
    //11、房产信息: 1：自有住房 , 2:贷款购房 , 3:与亲属合住 , 4: 租房 , 5:集体宿舍 , 9:其他
    private final SparseArray<String> houseInformation;
    //12、邮寄地址类型: 1：住宅地址 , 2:单位地址
    private final SparseArray<String> mailAddress;
    //13、约定还款方式: 1：全额还款 , 2:最低还款额
    private final SparseArray<String> repayType;
    //14、推荐人和申请人关系: 1：亲戚 , 2:朋友 ，3:客户 , 4:同学 , 9: 其他
    private final SparseArray<String> recommendType;
    //15、已核实内容: 1：亲见申请人签名及身份证原件 , 2:确认申请人家庭状况 ，3:亲访申请人单位并确认工作 , 4:合影
    private final SparseArray<String> proveDetails;
    //16、申请来源: 1:行员推荐 , 2:熟人介绍 ，3:柜台拜访 , 4:陌拜推广
    private final SparseArray<String> sourceType;
    //17、申请人与我行来往方式: 1:理财客户 , 2:代发工资户 ，3:个人存款户 , 4:个人贷款户 , 5:对公单位员工 , 9:其他
    private final SparseArray<String> dealingsType;

    public HashMap<String, String> getIsOption() {
        return isOption;
    }

    public SparseArray<String> getGender() {
        return gender;
    }

    public SparseArray<String> getCredentialType() {
        return credentialType;
    }

    public SparseArray<String> getCredentialValidTime() {
        return credentialValidTime;
    }

    public SparseArray<String> getMarriageState() {
        return marriageState;
    }

    public SparseArray<String> getEducation() {
        return education;
    }

    public SparseArray<String> getKinsfolkRelation() {
        return kinsfolkRelation;
    }

    public SparseArray<String> getInstancyRelation() {
        return instancyRelation;
    }

    public SparseArray<String> getIndustryType() {
        return industryType;
    }

    public SparseArray<String> getDutyType() {
        return dutyType;
    }

    public SparseArray<String> getHouseInformation() {
        return houseInformation;
    }

    public SparseArray<String> getMailAddress() {
        return mailAddress;
    }

    public SparseArray<String> getRepayType() {
        return repayType;
    }

    public SparseArray<String> getRecommendType() {
        return recommendType;
    }

    public SparseArray<String> getProveDetails() {
        return proveDetails;
    }

    public SparseArray<String> getSourceType() {
        return sourceType;
    }

    public SparseArray<String> getDealingsType() {
        return dealingsType;
    }
}
