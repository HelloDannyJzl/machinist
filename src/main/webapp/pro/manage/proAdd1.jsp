<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>添加项目</title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js" ></script>
        <script type="text/javascript">
        
        /**
        *提交表单添加数据
        */
        function checksubmit() {
	        var pronumber = $('#pronumber').val().trim();
	        var proname = $('#proname').val().trim();
	        var selProvince =$('#selProvince').val().trim();
	    	if(pronumber==""||proname==""||selProvince=="--请选择省份--"){
	    		alert("录入信息不能为空");
	    		return false;
	    	}
	    	return true;
        }
          
       /**
       *返回项目管理主页
       */
        function goBack(){
           location.href="<%=request.getContextPath()%>/project/queryAllProject.do";
        }
			
		/**
		*下拉框选省份点击事件
		*/
        function changecity(){
			var cityList=new Array();
			cityList['北京']=['东城','西城','崇文','宣武','朝阳','丰台','石景山','海淀','门头沟','房山','通州','顺义','昌平','大兴','平谷','怀柔','密云','延庆'];
			cityList['天津']=['和平','河东','河西','南开','河北','红桥','塘沽','汉沽','大港','东丽','西青','津南','北辰','宁河','武清','静海','宝坻','蓟县'];
			cityList['河北']=['石家庄','邯郸','邢台','保定','张家口','承德','廊坊','唐山','秦皇岛','沧州','衡水'];
			cityList['山西']=['太原','大同','阳泉','长治','晋城','朔州','吕梁','忻州','晋中','临汾','运城'];
			cityList['内蒙古']=['呼和浩特','包头','乌海','赤峰','呼伦贝尔盟','阿拉善盟','哲里木盟','兴安盟','乌兰察布盟','锡林郭勒盟','巴彦淖尔盟','伊克昭盟'];
			cityList['辽宁']=['沈阳','大连','鞍山','抚顺','本溪','丹东','锦州','营口','阜新','辽阳','盘锦','铁岭','朝阳','葫芦岛'];
			cityList['吉林']=['长春','吉林','四平','辽源','通化','白山','松原','白城','延边'];
			cityList['黑龙江']=['哈尔滨','齐齐哈尔','牡丹江','佳木斯','大庆','绥化','鹤岗','鸡西','黑河','双鸭山','伊春','七台河','大兴安岭'];
			cityList['上海']=['黄浦','卢湾','徐汇','长宁','静安','普陀','闸北','虹口','杨浦','闵行','宝山','嘉定','浦东','金山','松江','青浦','南汇','奉贤','崇明'];
			cityList['江苏']=['南京','镇江','苏州','南通','扬州','盐城','徐州','连云港','常州','无锡','宿迁','泰州','淮安'];
			cityList['浙江']=['杭州','宁波','温州','嘉兴','湖州','绍兴','金华','衢州','舟山','台州','丽水'];
			cityList['安徽']=['合肥','芜湖','蚌埠','马鞍山','淮北','铜陵','安庆','黄山','滁州','宿州','池州','淮南','巢湖','阜阳','六安','宣城','亳州'];
			cityList['福建']=['福州','厦门','莆田','三明','泉州','漳州','南平','龙岩','宁德'];
			cityList['江西']=['南昌','景德镇','九江','鹰潭','萍乡','新馀','赣州','吉安','宜春','抚州','上饶'];
			cityList['山东']=['济南','青岛','淄博','枣庄','东营','烟台','潍坊','济宁','泰安','威海','日照'];
			cityList['河南']=['郑州','开封','洛阳','平顶山','安阳','鹤壁','新乡','焦作','濮阳','许昌','漯河','三门峡','南阳','商丘','周口','信阳','驻马店','潢川','济源'];
			cityList['湖北']=['武昌','宜昌','荆州','襄樊','黄石','荆门','黄冈','十堰','恩施','潜江'];
			cityList['湖南']=['长沙','常德','株洲','湘潭','衡阳','岳阳','邵阳','益阳','娄底','怀化','郴州','永州','湘西','张家界'];
			cityList['广东']=['广州','深圳','珠海','汕头','东莞','中山','佛山','韶关','江门','湛江','茂名','肇庆','惠州','梅州','汕尾','河源','阳江','清远','潮州','揭阳','云浮'];
			cityList['广西']=['南宁','柳州','桂林','梧州','北海,','防城港','钦州','贵港','玉林','南宁地区','柳州地区','贺州','百色','河池'];
			cityList['海南']=['海口','三亚'];
			cityList['重庆']=['万州','涪陵','渝中','大渡口','江北','沙坪坝','九龙坡','南岸','北碚','万盛','双挢','渝北','巴南','黔江','长寿','綦江','潼南','铜梁','大足','荣昌','壁山','梁平','城口','丰都','垫江','武隆','忠县','开县','云阳','奉节','巫山','巫溪','石柱','秀山','酉阳','彭水','江津','合川','永川','南川'];
			cityList['四川']=['成都','绵阳','德阳','自贡','内江','乐山','南充','雅安','眉山','甘孜','凉山','泸州'];
			cityList['贵州']=['贵阳','六盘水','遵义','安顺','铜仁','黔西南','毕节','黔东南','黔南'];
			cityList['云南']=['昆明','大理','曲靖','玉溪','昭通','楚雄','红河','文山','思茅','西双版纳','保山','德宏','丽江','怒江','迪庆','临沧'];
			cityList['西藏']=['拉萨','日喀则','山南','林芝','昌都','阿里','那曲'];
			cityList['陕西']=['西安','宝鸡','咸阳','铜川','渭南','延安','榆林','汉中','安康','商洛'];
			cityList['甘肃']=['兰州','嘉峪关','金昌','白银','天水','酒泉','张掖','武威','定西','陇南','平凉','庆阳','临夏','甘南'];
			cityList['青海']=['西宁','海东','海南','海北','黄南','玉树','果洛','海西'];
			cityList['宁夏']=['银川','石嘴山','吴忠','固原'];
			cityList['新疆']=['乌鲁木齐','石河子','克拉玛依','伊犁','巴音郭勒','昌吉','克孜勒苏柯尔克孜','博尔塔拉','吐鲁番','哈密','喀什','和田','阿克苏'];
			cityList['台湾']=['台北','高雄','台中','台南','屏东','南投','云林','新竹','彰化','苗栗','嘉义','花莲','桃园','宜兰','基隆','台东','金门','马祖','澎湖'];
			cityList['香港']=['香港'];
			cityList['澳门']=['澳门'];
			
			//获得省份选项的索引，这里使用标识符
				//var pIndex=document.addItem.selProvince.value;
			var pIndex = document.getElementById("selProvince").value;
			var newOption1;
			//document.addItem.selCity.options.length=0;
			document.getElementById("selCity").options.length=0;
			for(var j in cityList[pIndex]){
				newOption1=new Option(cityList[pIndex][j],cityList[pIndex][j]);
				document.getElementById("selCity").options.add(newOption1);
			}
		}
			
			
        </script>
    </head>
    <body>
	   		<div style="background: PaleTurquoise ; min-height: 190px;margin-top: 0pt;">
		   		<div align="center">
					<h1><b>机械师制作团队</b></h1>
					<hr width="750px;"/>
					<h2><b>管理员/项目管理/添加项目</b></h2>
				</div>
			</div>
		
			
			<div style="margin-left:800px; ">
	        <form id="addProject" action="<%=request.getContextPath()%>/project/addProject.do">
	            <input type="hidden" id="type" name="type" height="60px"/>
	            项目编号：<input  style="width: 240px;height: 25px;background-color: Moccasin ;"    type="text" id="pronumber" name="pronumber" value="${pronumber}" readonly="readonly"/><br /><br />
	            项目名称：<input  style="width: 240px;height: 25px;background-color: Moccasin ;"    type="text" id="proname" name="proname" /><br /><br />
	            项目地址：<select id="selProvince" name="selProvince" onchange="changecity()">
				   <option>--请选择省份--</option>
				   <option value="北京">北京</option>
				   <option value="天津">天津</option>
				   <option value="河北">河北</option>
				   <option value="山西">山西</option>
				   <option value="内蒙古">内蒙古</option>
				   <option value="辽宁">辽宁</option>
				   <option value="辽宁">吉林</option>
				   <option value="黑龙江">黑龙江</option>
				   <option value="上海">上海</option>
				   <option value="江苏">江苏</option>
				   <option value="浙江">浙江</option>
				   <option value="安徽">安徽</option>
				   <option value="福建">福建</option>
				   <option value="江西">江西</option>
				   <option value="山东">山东</option>
				   <option value="河南">河南</option>
				   <option value="湖北">湖北</option>
				   <option value="湖南">湖南</option>
				   <option value="广东">广东</option>
				   <option value="广西">广西</option>
				   <option value="海南">海南</option>
				   <option value="重庆">重庆</option>
				   <option value="四川">四川</option>
				   <option value="贵州">贵州</option>
				   <option value="云南">云南</option>
				   <option value="西藏">西藏</option>
				   <option value="陕西">陕西</option>
				   <option value="甘肃">甘肃</option>
				   <option value="青海">青海</option>
				   <option value="宁夏">宁夏</option>
				   <option value="新疆">新疆</option>
				   <option value="台湾">台湾</option>
				   <option value="香港">香港</option>
				   <option value="澳门">澳门</option>
			   </select>
			   <select id="selCity" name="selCity"><option>--请选择城市--</option></select>
  			   <input type="text" id="proSite" name="proSite" value="--地点详情--"  
  			   		onfocus="if(value=='--地点详情--'){value=''}"   
                	onblur="if(value==''){value='--地点详情--'}" style="width:120px;height:11px"/>	<br/><br/><br/>
	           <input style= "height:25px;width:130px; background-color: PaleTurquoise ;" type="button" value="返回项目管理主页" onclick="goBack();" />&nbsp;&nbsp;&nbsp;&nbsp;
	           <input  style= "height:25px;width:130px; background-color: PaleTurquoise;" type="submit" value="添加项目" onclick="return checksubmit()" />
	        </form>
	        </div>
    </body>
</html>

