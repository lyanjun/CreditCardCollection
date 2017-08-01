package com.bank.creditcardcollection.model.entity;

/**
 * 新增信用卡信息
 * Created by liyanjun on 2017/7/31.
 */

public class ApplyInfo {

    /**
     * 必填字段
     */
    private String name;//VARCHAR2(60)	N	中文姓名    2
    private String nation;//VARCHAR2(60)	N	国籍  2
    private String pinyin;//VARCHAR2(90)	N	拼音姓名    2
    private String idno;//VARCHAR2(32)	N	证件号码    2
    private String idvalidate;//CHAR(10)	N	证件有效期 2
    private String tel;//VARCHAR2(20)	N	移动电话    2
//    private String email;//VARCHAR2(32)	N	电子邮箱    3
    private String province;//VARCHAR2(60)	N	居住省份    2
    private String city;//VARCHAR2(60)	N	居住城市    2
    private String area;//VARCHAR2(60)	N	居住区县    2
    private String address;// VARCHAR2(90)	N	居住详细地址  2
    private String familycontactname;//VARCHAR2(60)	N	直系亲属姓名  2
    private String familycontactrel;//CHAR(1)	N	直系亲属关系  2
    private String familycontacttel;//VARCHAR2(20)	N	直系亲属移动电话    2
    private String emergencycontactname;//VARCHAR2(60)	N	紧急联系人姓名     2
    private String emergencycontactrel;//CHAR(1)	N	紧急联系人关系     2
    private String emergencycontacttel;//VARCHAR2(20)	N	紧急联系人移动电话   2
    private String companyname;//VARCHAR2(180)	N	申请人单位名称     3
    private String department;//VARCHAR2(60)	N	任职部门    3
    private String comprovince;//VARCHAR2(60)	N	工作单位省份  3
    private String comcity;// VARCHAR2(60)	N	工作单位城市  3
    private String comarea;//VARCHAR2(60)	N	工作单位区县  3
    private String comaddress;//VARCHAR2(90)	N	工作单位详细地址    3
    private String comphone;//VARCHAR2(16)	N	工作单位电话及区号   3
    private Integer incoming;//INTEGER	N	税后年收入万元     3
    private String posttype;//CHAR(1)	N	邮寄地址        3
    private String ebilladdr;//VARCHAR2(32)	N	电子对账单发送地址       3
    private String createtime;//VARCHAR2(20)	N	创建时间        ?
    private String status;// CHAR(1)	N	申请状态: 1:申请中; 2:预审不通过; 3:复审不通过; 4:申请成功       ?

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getIdvalidate() {
        return idvalidate;
    }

    public void setIdvalidate(String idvalidate) {
        this.idvalidate = idvalidate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFamilycontactname() {
        return familycontactname;
    }

    public void setFamilycontactname(String familycontactname) {
        this.familycontactname = familycontactname;
    }

    public String getFamilycontactrel() {
        return familycontactrel;
    }

    public void setFamilycontactrel(String familycontactrel) {
        this.familycontactrel = familycontactrel;
    }

    public String getFamilycontacttel() {
        return familycontacttel;
    }

    public void setFamilycontacttel(String familycontacttel) {
        this.familycontacttel = familycontacttel;
    }

    public String getEmergencycontactname() {
        return emergencycontactname;
    }

    public void setEmergencycontactname(String emergencycontactname) {
        this.emergencycontactname = emergencycontactname;
    }

    public String getEmergencycontactrel() {
        return emergencycontactrel;
    }

    public void setEmergencycontactrel(String emergencycontactrel) {
        this.emergencycontactrel = emergencycontactrel;
    }

    public String getEmergencycontacttel() {
        return emergencycontacttel;
    }

    public void setEmergencycontacttel(String emergencycontacttel) {
        this.emergencycontacttel = emergencycontacttel;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getComprovince() {
        return comprovince;
    }

    public void setComprovince(String comprovince) {
        this.comprovince = comprovince;
    }

    public String getComcity() {
        return comcity;
    }

    public void setComcity(String comcity) {
        this.comcity = comcity;
    }

    public String getComarea() {
        return comarea;
    }

    public void setComarea(String comarea) {
        this.comarea = comarea;
    }

    public String getComaddress() {
        return comaddress;
    }

    public void setComaddress(String comaddress) {
        this.comaddress = comaddress;
    }

    public String getComphone() {
        return comphone;
    }

    public void setComphone(String comphone) {
        this.comphone = comphone;
    }

    public Integer getIncoming() {
        return incoming;
    }

    public void setIncoming(Integer incoming) {
        this.incoming = incoming;
    }

    public String getPosttype() {
        return posttype;
    }

    public void setPosttype(String posttype) {
        this.posttype = posttype;
    }

    public String getEbilladdr() {
        return ebilladdr;
    }

    public void setEbilladdr(String ebilladdr) {
        this.ebilladdr = ebilladdr;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 第二页
     *
     * @return
     */
    public void setLevel2Info(ApplyInfo changeInfo) {
        setName(changeInfo.getName());
        setNation(changeInfo.getNation());
        setPinyin(changeInfo.getPinyin());
        setIdno(changeInfo.getIdno());
        setIdvalidate(changeInfo.getIdvalidate());
        setTel(changeInfo.getTel());
        setProvince(changeInfo.getProvince());
        setCity(changeInfo.getCity());
        setArea(changeInfo.getArea());
        setAddress(changeInfo.getAddress());
        setFamilycontactname(changeInfo.getFamilycontactname());
        setFamilycontactrel(changeInfo.getFamilycontactrel());
        setFamilycontacttel(changeInfo.getFamilycontacttel());
        setEmergencycontactname(changeInfo.getEmergencycontactname());
        setEmergencycontactrel(changeInfo.getEmergencycontactrel());
        setEmergencycontacttel(changeInfo.getEmergencycontacttel());
    }

    public String toStringApplyInfoLevel2() {
        StringBuilder builder = new StringBuilder();
        builder.append("中文姓名 : ").append(name).append("\n")
                .append("国籍 : ").append(nation).append("\n")
                .append("拼音姓名 : ").append(pinyin).append("\n")
                .append("证件号码 : ").append(idno).append("\n")
                .append("证件有效期 : ").append(idvalidate).append("\n")
                .append("移动电话 : ").append(tel).append("\n")
                .append("居住省份 : ").append(province).append("\n")
                .append("居住城市 : ").append(area).append("\n")
                .append("居住详细地址 : ").append(address).append("\n")
                .append("直系亲属姓名 : ").append(familycontactname).append("\n")
                .append("直系亲属关系 : ").append(familycontactrel).append("\n")
                .append("直系亲属移动电话 : ").append(familycontacttel).append("\n")
                .append("紧急联系人姓名 : ").append(emergencycontactname).append("\n")
                .append("紧急联系人关系 : ").append(emergencycontactrel).append("\n")
                .append("紧急联系人移动电话 : ").append(emergencycontacttel).append("\n");
        return builder.toString();
    }

    /**
     * 第三页
     *
     * @return
     */
    public void setLevel3Info(ApplyInfo changeInfo) {
        setCompanyname(changeInfo.getCompanyname());
        setDepartment(changeInfo.getDepartment());
        setComprovince(changeInfo.getComprovince());
        setComcity(changeInfo.getComcity());
        setComarea(changeInfo.getComarea());
        setComaddress(changeInfo.getComaddress());
        setComphone(changeInfo.getComphone());
        setIncoming(changeInfo.getIncoming());
        setPosttype(changeInfo.getPosttype());
        setEbilladdr(changeInfo.getEbilladdr());
    }
    public String toStringApplyInfoLevel3() {
        StringBuilder builder = new StringBuilder();
        builder
//                .append("电子邮箱 : ").append(email).append("\n")
                .append("申请人单位名称 : ").append(companyname).append("\n")
                .append("任职部门 : ").append(department).append("\n")
                .append("工作单位省份 : ").append(comprovince).append("\n")
                .append("工作单位城市 : ").append(comcity).append("\n")
                .append("工作单位区县 : ").append(comarea).append("\n")
                .append("工作单位详细地址 : ").append(comaddress).append("\n")
                .append("工作单位电话及区号 : ").append(comphone).append("\n")
                .append("税后年收入万元 : ").append(incoming).append("\n")
                .append("邮寄地址 : ").append(posttype).append("\n")
                .append("电子对账单发送地址 : ").append(ebilladdr).append("\n");
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(toStringApplyInfoLevel2()).append(toStringApplyInfoLevel3())
                .append("创建时间 : ").append(createtime).append("\n")
                .append("申请状态 : ").append(status).append("\n");
        return stringBuilder.toString();
    }


}
