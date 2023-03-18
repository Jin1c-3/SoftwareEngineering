# coding:utf-8
import time

import requests
import json


def get_data(address):
    url = f'https://lbs.amap.com/_AMapService/v3/place/text?s=rsv3&children=&key=f7d40927ba4d64fb91ebe2bb9cda0995&offset=1&page=1&extensions=all&city=110000&language=zh_cn&callback=jsonp_880297_&platform=JS&logversion=2.0&appname=https%3A%2F%2Flbs.amap.com%2Ftools%2Fpicker&csid=521BF098-DA1B-4911-8F93-4F31BD4300C9&sdkversion=1.4.22&keywords={address}'
    headers = {
        'Cookie': '_uab_collina=167854142191496206043746; AMAPID=83d0cc2b57bb4de491844e0da75e88c1; passport_login=NDUyMjYxNjEzLGFtYXBfMTgyODQ1NTkyNTVCR2VJcm5BN2osa3M0YWg1Nng3aWNvcTdwaHc3M2dnbHR4bDV3ZXhlZXIsMTY3ODYwMDY4Myxaakk1T1RVNU1tSTVOVE15TlRrNVkyRTNZVGRpTm1NM01EaGlObVl4T1RRPQ%3D%3D; x5sec=7b227761676272696467652d616c69626162612d616d61703b32223a2263333331623664356634636234633466306161363432396233393139323434324350366874364147454e69347a7065422b4b6e4e34414577684c715857454144227d; l=fBSDqA04TeyGzGeNBO5BKurza77T1BdfllVzaNbMiIEGa1PRKU6YEOCsOddwjdtjgT5cRn-PDzebmd3k5rULRrOdew2F5156VQv68eZl2R2d.; isg=BHFxPrRlsQ0qxRrZgsAVfm3lgP0LXuXQUga3tFOBhThXepvMhKy9oIlcnA4ciX0I'

        , 'Host': 'lbs.amap.com'
        , 'Referer': 'https://lbs.amap.com/'
        ,
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36 Edg/110.0.1587.63'
    }

    response = requests.get(url, headers=headers)
    result = response.text[14:-1]
    result = json.loads(result)  # 如果报错，可以print(result)看一下
    if result['status'] == "0":  # 运行过程中，可能出现无法根据地址搜索到省市区的情况，因此添加if和elif语句
        print(f'{address}没找到1')
        return
    elif not result['pois']:
        print(f'{address}没找到2')
        return
    else:  # city、province、space、district如果不知道怎么来的，可以print（result）看一下
        city = result['pois'][0]['cityname']
        province = result['pois'][0]['pname']
        space = result['pois'][0]['name']
        district = result['pois'][0]['adname']
        print(f'{space},{province},{city},{district}')
        time.sleep(1)
        return


# str = '北京北@北京东@北京@北京南@北京大兴@北京西@北京朝阳@重庆北@重庆北@重庆@重庆南@重庆西@重庆西@上海@上海南@上海虹桥@上海西@天津北@天津@天津南@天津西@滨江@百浪@班猫箐@北营@长春@长春南@长春西@成都东@成都东@成都南@成都@成都@成都西@陈官营@长沙@长沙南@长沙西@常庄@大成@大拟@读书铺@大王滩@大元@丰水村@福州@福州@福州南@福州南@甘草店@钢城@孤家子@广南卫@贵阳@贵阳北@贵阳北@贵阳东@广州北@广州东@广州@广州南@广州西@哈尔滨北@哈尔滨@哈尔滨@哈尔滨东@哈尔滨西' \
# str = '@合肥北城@合肥@合肥南@合肥南@皇姑屯@呼和浩特东@呼和浩特@海口东@海口@杭州东@杭州@杭州南@金马村@济南@济南@济南东@济南西@济南西@昆明@昆明南@历城@蔺家楼@龙泉寺@拉萨@乐善村@林盛堡@骆驼巷@莱芜北@兰州东@兰州@兰州新区@兰州西@茂舍祖@南昌@南昌@宁村@南昌西@南京@南京南@那罗@南宁东@南宁@南宁西@那铺@暖泉@坡底下@七甸@世博园@石家庄北@石家庄东@邵家堂@石家庄@施家嘴@沈阳@沈阳北@沈阳东@沈阳南@水源@沈阳西@桑园子@太原北@太原东@太原南@太原@武汉@武汉东@王家湾@乌鲁木齐南@乌鲁木齐@吴圩机场@王兆屯@西安北@西安@西固城@西街口@许家台@西宁@小哨@雪野@银川@永丰营@一间堡@宜耐@羊堡@榆树台@引镇@朱家窑@章丘南@郑州东@郑州航空港*@郑州@郑州西@昂昂溪@阿城北@阿城@安达@安德@阿尔山北@阿尔山@安吉@安靖@安家@安康@阿克苏@阿克陶@阿拉尔@阿里河@阿拉山口@阿勒泰@安陆@安陆西@安平@安庆@安庆西@安顺@鞍山@安顺西@鞍山西@安亭北@安亭西@安阳@安阳东@北安@博白@蚌埠南@蚌埠@巴楚@白城@北辰@宝坻北@八达岭长城@保定东@北戴河@保定@八达岭@巴东@八方山@柏果@北海@布海@滨海@滨海北@白河@滨海西@毕节@宝鸡' \
# str = '@白涧@宝鸡南@北京丰台@白奎堡@博克图@博乐@巴林@勃利@白马井@八面通@北票@宝清@宝泉岭@百色@白山市@北台@包头东@包头东@包头@北屯市@宾西北@本溪@步行街@宾阳@白云鄂博@白云北@白云机场北@白洋淀@背荫河@百宜@巴彦高勒@鲅鱼圈@白银西@白云西@彬州东@巴中@滨州@亳州@宾州@亳州南@查布嘎@赤壁@长白山@常德@承德@长甸@承德南@曹妃甸东@赤峰@曹妃甸港@赤峰南@嵯岗@柴岗@长葛北@柴沟堡@城固@成高子@草海@巢湖东@柴河@巢湖@从江@蔡家崖@长乐东@长乐@长临河@慈利@茶陵@崇礼@昌黎@长流@长乐南@晨明@苍南@昌平北@常平东@昌平@长庆桥@崇仁@长寿北@潮汕@察素齐@朝天@长汀南@朝天南@昌图@昌图西@长汀镇@长武@苍溪@辰溪@磁县@楚雄@曹县@城西@长兴南@陈相屯@春阳@潮阳@朝阳川@朝阳湖@滁州北@常州北@长治北@长治东@长征@池州@滁州@郴州@沧州@常州@长治@崇州@崇左南@崇左@郴州西@沧州西@大安北@东安东@达坂城@定边@东岔@丹东@东方@丹凤@大丰@东方红@大方南@东风南@东港北@东莞东@东莞南@大孤山@东莞@东莞西@大红旗@大虎山@敦化@敦煌@德惠@德惠西@东京城@达家沟@垫江@道滘@大涧@杜家@洞井@都江堰@洞口@大连北@德令哈@达连河@大荔@大理@大连@大明湖@得莫利@东明县@定南@定南南@大埔@大庆东@大庆@对青山@大庆西@东胜@独山@砀山南@大石桥@东胜西@大同南@大同@大屯@大通西@大武口@党武@定西北@大兴机场@定西@东乡@大兴@德阳@当阳@丹阳@大冶北@大英东@都匀东@东营@大邑@东营南@大杨树@都匀@德州东@定州东@邓州东@东至@达州@德州@定州@邓州@峨边@鄂尔多斯@额济纳@二连@峨眉@峨眉山@恩施@鄂州@' \
# str = '防城港北@福鼎@肥东@丰都@发耳@福海@凤凰机场@凤凰城@汾河@奉化@富锦@范家屯@涪陵北@风陵渡@涪陵@富拉尔基@福利区@阜宁东@富宁@福清@福泉@芙蓉南@抚顺北@富顺@佛山@扶绥@佛山西@福田@凤县@阜新@肥西@阜新南@阜阳@富阳@扶余北@分宜@富蕴@富源@抚远@富裕@阜阳西@丰镇@凤州@抚州@方正@广安南@广安@高安@贵安@贵安@古北口@藁城@藁城南@高村@古东@格尔木@贵港@甘谷@根河@高花@古交@皋兰@桂林北@高楞@桂林@古莲@甘洛@公庙子@广南县@桂平@共青城@固始@广水@谷山@观沙岭@干塘@广通北@古田会址@高兴@高邑@巩义@巩义南@固原@广元@赣榆@高邑西@高州@赣州@公主岭@公主岭南@冠豸山@盖州西@赣州西@淮安东@淮安@红安西@淮北@鹤北@淮滨@河边@湖潮东@韩城@合川@珲春@潢川@海城@花城街@黄村@海城西@邯郸@河东机场@邯郸东@惠东@哈达铺@花都@洪洞西@横道河子@霍尔果斯@鹤岗@黄冈东@红果@汉沽@红光镇@红河@黑河@浑河@怀化南@黄河景区@怀化@后湖@和静@河津@怀集@华家@河口北@宏克力@河口南@汉口@呼兰@葫芦岛北@葫芦岛@海拉尔@哈拉海@寒岭@海林@虎林@霍林郭勒@黄陵南@海伦@侯马@黄梅东@鲘门@海门@哈密@侯马西@淮南@桦南@淮南东@淮南南@海宁西@鹤庆@怀柔北@怀仁东@怀柔南@怀柔@华山北@衡水北@黄山北@黄石东@和什托洛盖@华山@和硕@黑水@衡水@黄石@黄山@花山南@黑山寺@虎石台@海石湾@花山镇@黄土店@花土沟@和田@会同@海湾@花溪大学城@环县@花溪南@花溪西@衡阳@河源东@衡阳东@华蓥@鄠邑@汉源@河源@湟源@惠州北@菏泽东@菏泽@贺州@华州@湖州@汉中@惠州@惠州南@吉安@集安@建安@吉安西@江边村@晋城东@金昌@晋城@金城江@景德镇北@建德@鸡东@景德镇@嘉峰@加格达奇@井冈山@近海@静海@蛟河@精河南@金华南@金华@蛟河西@金华镇@晋江@九江@军粮城北@贾鲁河@吉林@即墨北@江门@荆门@' \
# str = '剑门关@佳木斯@佳木斯@井南@建宁县北@济宁@江宁@集宁南@江宁西@经棚@建平@酒泉南@酒泉@金山北@吉首东@吉首@江山@尖山@建三江@界首南@九台@九台南@镜铁山@绩溪北@介休东@介休@靖西@嘉兴@鸡西@井陉@进贤@嘉兴南@进贤南@绩溪县@鸡西西@金阳@巨野@嘉峪关@嘉峪关南@金阳南@简阳南@江油@金银潭@靖宇@锦州北@蓟州北@荆州@金寨@锦州@金州@晋州@蓟州@锦州南@焦作@焦作西@开安@库车@库都尔@库尔勒@开封北@开封@开福寺@开化@康金井@岢岚@凯里@凯里南@库伦@开鲁@克拉玛依@喀什@昆山@克山@昆山南@奎屯@开阳@昆阳@开原@开原西@康庄@喀左@隆安东@六安@灵宝@来宾北@灵宝西@绿博园@临沧@隆昌北@乐昌东@芦潮港@陆川@龙川@' \
# str = '利川@临川@隆昌@潞城@聊城@陵城@龙川西@老城镇@两当@鹿道@龙洞堡@娄底@娄底南@离堆公园@廊坊@娄烦@廊坊北@陆丰@临汾@临汾西@拉古@芦官@麓谷@良各庄@临河@柳河@漯河@六合@珞璜南@隆回@隆化@绿化@漯河西@刘家店@龙井@临江@丽江@龙嘉@庐江@连江@庐江西@兰考@兰考南@林口@龙口市@吕梁@醴陵@兰棱@拉林@柳林南@陇南@辽宁朝阳@梁平@滦平@罗平@梁平南@临平南@六盘水@灵丘@龙桥@龙山北@灵石东@乐山@陵水@丽水@旅顺@庐山@溧水@岚山西@黎塘@芦台@临潼@乐同@灵武北@莱芜东@洛湾三江@泸县@澧县@陇西@陇县@临西@莱西@兰溪@良乡@略阳@辽阳@耒阳@溧阳@龙岩@龙岩@洛阳@' \
# str = '临沂北@临沂北@连云港东@洛阳东@连云港@临沂@洛阳龙门@柳园南@凌源@辽源@柳园@涟源@涞源@罗源@耒阳西@泸州@林芝@柳州@六枝@阆中@龙镇@立志@辽中@马鞍山东@麻城北@麻城@渑池南@免渡河@牡丹江@牡丹江@莫尔道嘎@帽儿山@帽儿山西@明光@满归@孟关@磨憨@漠河@梅河口@民和南@孟家岗@米兰@勐腊@美兰@弥勒@穆棱@茂名@茂名西@冕宁@玛纳斯@闽清北@民权@眉山东@名山@密山@庙山@马三家@米沙子@麻尾@岷县@勉县@绵阳@密云北@孟塬@墨玉@门源@暮云@密云@梅州@孟庄@蒙自@满洲里@梅州西@宁安@农安@宁波东@宁波@南部@南曹@南充北@南充@南城@南岔@南丹@宁德@南大庙@宁东南@宁东@南芬@南丰@宁海@南湖东@讷河@牛河梁@内江北@内江@嫩江@南江@牛家@南口@牛栏山@宁陵县@奈曼@尼木@南平市@宁强南@那曲@南通@南通西@宁武@南翔北@南雄@宁乡@南阳@南阳东@纳雍@南峪@南阳寨@碾子山@普安@蒲城东@平昌@平顶山@平度@平度西@平顶山西@普洱@平房@盘锦北@盘锦@蒲江@盘龙城@普兰店@平凉@平凉南@蓬莱市@普宁@平泉北@平泉@皮山@磐石@坪石@平山@平潭@莆田@萍乡北@凭祥@萍乡@普雄@郫县@郫县西@濮阳@平阳@平遥古城@濮阳东@平原东@彭泽@普者黑@盘州@攀枝花@彭州@攀枝花南@彭州南@庆安@青白江东@清城@蕲春@青川@青城山@青岛@青岛北@青岛北@千岛湖@启东@青岛西@曲阜东@前锋@曲阜@' \
# str = '琼海@清河城@秦皇岛@清河@清华园@曲靖北@綦江东@黔江@曲靖@前进镇@邛崃@清流@齐齐哈尔@齐齐哈尔南@潜山@庆盛@曲水县@七台河@青铜峡@七台河西@渠县@沁县@清徐@庆阳@清远@庆元@钦州东@泉州东@乔庄东@衢州@泉州@全州南@清镇西@融安@瑞安@荣昌北@荣成@如东@汝箕沟@瑞金@日喀则@饶平@若羌@日照@日照西@肃北@双城北@舒城东@莎车@沙城@宋城路@双城堡@邵东@十渡@双峰北@双丰@绥芬河@韶关东@韶关@沙河@' \
# str = '商河@山海关@沙河市@山河屯@绥化@石河子@三家店@三间房@松江河@水家湖@松江@孙家@沈家@三江南@石景山南@' \
# str = '松江南@苏家屯@三江县@深井子@四棵树@舒兰@双流机场@双龙湖@绥棱@狮岭@石林@双龙南@商洛@双流西@石林西@胜利镇@石门县北@三明北@三明@嵩明@树木岭@神木南@三门峡南@神木@三门县@三门峡西@三门峡@商南@遂宁@睢宁@宋@石牌@沙坪坝@四平东@山坡东@四平@沈丘北@宿迁@商丘@石泉县@石桥子@上饶@石人城@鄯善北@宿松东@蜀山东@韶山@神树@韶山南@宿松@三穗@松桃@汕头@汕尾@邵武@绍兴北@绍兴东@松溪@涉县@绍兴@三亚@邵阳@十堰@双阳@十堰东@顺义@三元区@双鸭山@松原@双鸭山西@顺义西@深圳北@苏州北@深圳机场@嵊州新昌@深圳东@宿州东@绥中@朔州@深圳@随州@宿州@苏州@尚志@随州南@尚志南@深圳坪山@石嘴山@石柱县@深圳西@泰安@通北@铜川东@塔城@汤池@通道@土地堂东@塔尔气@潼关@太谷@塘沽@吐哈@通海@塔哈@天河机场@泰和@塔河@天河街@太湖南@天河潭@通化@太湖@同江@陶家屯@托克托东@泰来@吐鲁番北@吐鲁番@通辽@铜陵@铁岭@铁力@桐庐@铁岭西@陶赖昭@图们北@图们@头门港@图木舒克@天门南@潼南@泰宁@铜仁@铜仁南@唐山北@田师府@泰山@唐山@天水@天水南@汤旺河@汤逊湖@土溪@通远堡@太阳升@通榆@桐梓北@太子城@滕州东@桐梓东@台州@泰州@通州@台州西@通州西@文昌@武昌@五常@武当山@潍坊@瓦房店@万发屯@瓦房店西@王岗@武功@威海@乌海@苇河@芜湖@乌海西@苇河西@温江@五家@吴家屯@五棵树@乌兰察布@万乐@温岭@乌龙泉南@武隆@乌拉特前旗@乌兰浩特@渭南@渭南北@五女山@渭南西@沃皮@汪清@武清@武胜@威舍@乌审旗@乌苏@歪头山@武威@武威南@无为南@武穴北@无锡东@无锡@乌西@武穴@吴圩@闻喜西@武夷山北@五营@乌伊岭@武夷山@渭源@婺源@万源@万州北@梧州@万州@吴忠@温州@梧州南@温州南@兴安北@雄安@西安西@许昌东@兴城@宣城@西昌@许昌@西昌西@新城子@新都东@香坊@咸丰@西丰@息烽@先锋@湘府路@轩岗@孝感北@孝感东@香港西九龙@' \
# str = '兴国@西固@夏官营@宣汉@兴和北@下花园北@新化南@新会@新晃@兴和西@新晃西@新津@辛集@徐家@小金口@新津南@辛集南@谢家镇@西来@兴隆店@新乐@仙林@小岭@锡林浩特@兴隆县@新立镇@兴隆镇@厦门北@新民北@厦门@厦门@新民@厦门高崎@咸宁南@犀浦东@溆浦南@霞浦@溆浦@犀浦@秀山@小市@兴山@西双版纳@新松浦@仙桃@湘潭@向塘@邢台东@新塘南@兴文@宣威@修文县@萧县北@新香坊北@新乡东@孝西@西乡@西峡@新乡@小新街@信阳@旬阳@咸阳@岫岩@襄阳@新余北@熊岳城@信阳东@襄阳东@兴义@信宜@秀英@祥云@新余@咸阳西@新郑机场@徐州东@忻州@新肇@襄州@徐州@香樟路@忻州西@雅安@延安@永安南@依安@宜宾@迎宾路@亚布力@亚布力南@叶柏寿@宜宾西@亚布力西@运城北@盐城北@永川东@宜昌东@岳池@叶城@阳春@宜春@运城@宜昌@盐城@伊春@榆次@杨村@永登@雁荡山@于都@姚渡@英德西@伊尔施@云浮东@燕岗@永济北@延吉@阳江@永济@燕郊@姚家@英吉沙@延吉西@营口东@永康南@营口@牙克石@依兰@宜良北@永乐店@玉林@榆林@杨陵@炎陵@阎良@杨林@杨陵南@余粮堡@杨柳青@亚龙湾@羊马@一面坡北@云梦东@玉门@一面坡@元谋西@郁南@伊宁东@伊宁@延平东@阳平关@玉屏@延平@原平@延平西@原平西@阳泉北@阳泉东@雁栖湖@焉耆@乐清@延庆@阳泉曲@姚千户屯@阳泉@阳曲@玉泉@阳曲西@榆社@玉山@营山@榆树@元氏@燕山@玉山南@榆树屯@银滩@烟台@鹰潭@永泰@鹰潭北@伊图里河@依吞布拉克@烟台南@烟筒山@玉田县@义乌@玉溪@云霄@义县@阳新@宜兴@尤溪@益阳@岳阳@岳阳东@益阳南@扬州东@崖州@永州@兖州@扬州@榆中@诏安@淄博北@淄博@中川机场@镇城底@正定机场@正定@准东@纸坊东@柘皋@自贡北@自贡@珠海@' \
# str = '庄河北@珠海北@珠海长隆@中华门@张家川@张家港@湛江@织金@治江@芷江@诸暨@镇江@周家@张家界@张家口@张家口南@镇江南@湛江西@张家界西@周口东@周口@镇赉@庄里@左岭@扎兰屯@扎赉诺尔西@驻马店@中牟@驻马店西@漳平@泽普@漳平西@章丘北@肇庆东@肇庆@章丘@柞水@珠斯花@中山@樟树@朱砂古镇@周水子@中堂@昭通@中卫@中卫南@镇雄@紫阳@枣阳@' \
# str = '资阳北@张掖@遵义@镇远@遵义西@张掖西@资中北@漳州东@涿州东@枣庄@漳州@株洲@庄寨@株洲南@枣庄西@株洲西@阿巴嘎旗@安定@安多@安广@敖汉@艾河@安化@艾家村@安江东@阿金@安匠@阿克塞@安口窑@敖力布告@安龙@阿龙山@阿木尔@' \
# str = '阿南庄@安仁@安塘@阿图什@安图@安图西@阿瓦提@安溪@博鳌@白壁关@八步@栟茶@板城@宝坻@宝坻南@宝丰@白沟@白河东@滨海港@宝华山@白河县@白芨沟@北滘@' \
# str = '碧江@白鸡坡@笔架山@八角台@北井子@保康@保康县@白狼@博罗北@博乐东@北流@宝林@布列开@宝龙山@百里峡@八里甸子@白马北@八面城@北马圈子@北票南@白旗@白泉@璧山@巴山@白水江@白沙铺@白沙坡@白石山@白水县@白水镇@' \
# str = '板塘@坂田@泊头@北屯@巴图营@白文东@本溪新城@本溪湖@博兴@八仙筒@白音察干@宝应@白音他拉@白音华南@白音胡硕@' \
# str = '白银市@霸州北@巴中东@彬州@霸州@北宅@霸州西@长安@长安西@赤壁北@澄城@长城@承德县北@承德东@城固北@长葛@查干湖@册亨@草河口@崔黄口@蔡家沟@成吉思汗@岔江@陈江南@蔡家坡@策勒@昌乐@超梁沟@茶陵南@长岭子@长宁@长农@常平@长坡岭@常平南@长箐@辰清@长寿@长寿湖@蔡山@苍石@草市@磁山@常山@常熟@楚山@长山屯@长汀@春湾@岑溪@长兴@磁西@磁窑@长阳@城阳@创业村@朝阳地@昌邑@朝阳南@长垣@朝阳镇@陈庄@潮州@曹子里@长治南@城子坦@车转湾@大安@德安@大坝@德保@到保@大巴@电白@大板@东边井@德伯斯@打柴沟@德昌@大厂@都昌@东城南@德昌西@滴道@大磴沟@东戴河@丹东西@刀尔登@得耳布尔@东二道河@杜尔伯特@大方@东丰@都格@东莞港@大港南@大官屯@东光@东海@东花园北@大灰厂@鼎湖东@鼎湖山@大禾塘@东海县@东津@丹江口@董家口@大口屯@东来@大林@带岭@达拉特旗@独立屯@豆罗@达拉特西@大连西@大朗镇@东明村@洞庙河@大平房@大盘石@大堡@大青沟@大其拉哈@道清@德清@杜桥@德清西@东胜东@东升@登沙河@砀山@大石头南@大石头@大石寨@灯塔@定陶@东台@大田边@当涂东@东通化@丹徒@东湾@大旺@低窝铺@德兴东@大兴沟@德兴@定襄@代县@甸心@丹霞山@东戌@东辛庄@大雁@大阳@丹阳北@东淤地@大营@定远@岱岳@大余@大营子@大营镇@大战场@' \
# str = '兑镇@道州@东镇@东庄@端州@低庄@豆庄@大足南@大竹园@大杖子@豆张庄@峨边南@二道沟门@二道湾@二龙@二龙山屯@二密河@额敏@恩平@峨山@二营@鄂州东@福安@丰城东@凤城东@富川@丰城@方城@丰城南@繁昌西@扶沟南@富海@凤凰古城@福海西@奉节@枫林@福利屯@丰乐镇@阜南@抚宁@阜宁@阜宁南@富平@佛坪@法启@芙蓉镇@丰顺东@复盛@丰顺@繁峙@抚顺@福山口@扶绥南@抚松@福山镇@凤台南@冯屯@浮图峪@费县北@富县东@富县@费县@汾阳@凤阳@富源北@扶余@抚州北@抚州东@范镇@固安东@固安@高碑店东@高碑店@沟帮子@谷城北@古城东@恭城@谷城@古城镇@贵定北@广德@贵定@广德南@葛店南@贵定县@岗嘎@贡嘎@官高@葛根庙@干沟@高各庄@广汉北@甘河@郭家店@个旧@古浪@橄榄坝@归流河@' \
# str = '关岭@关林@甘洛南@桂林西@郭磊庄@高密北@光明城@高密@灌南@工农湖@广宁@广宁寺南@广宁寺@高平东@高坪@广平@高平@弓棚子@甘泉北@甘旗卡@甘泉@高桥镇@赶水东@光山@灌水@孤山口@果松@嘎什甸子@高山子@高滩@高台@古田北@古田@官厅@高台南@官厅西@赣县北@贵溪@涡阳@高邮北@观音机场@高邮@菇园@灌云@公营子@光泽@古镇@虢镇@盖州@瓜州@固镇@官字井@冠豸山南@古丈西@红安@海安@淮安南@怀安@惠安@惠安堡@黄柏@淮北北@鹤壁东@海北@鹤壁@会昌北@寒葱沟@河唇@' \
# str = '华城@霍城@汉川@黑冲滩@横道河子东@化德@河东里@海东@洪洞@海东西@横峰@韩府湾@黄冈@横沟桥东@黄冈西@洪河@红花沟@黄花筒@惠环@花湖@贺家店@厚街@黑井@涵江@获嘉@杭锦后旗@河间西@花家庄@黄口@湖口@怀来@海林北@浩良河@黄流@黄陵@鹤立@桦林@和龙@海龙@哈拉苏@呼鲁斯太@火连寨@虎门北@虎门东@黄梅@虎门@洪梅@韩麻营@衡南@桦南东@黄泥河@化念@海宁@怀宁@惠农@和平北@和平@合浦@花棚子@横琴北@霍邱@宏庆@横琴@花桥@红旗渠@华容东@怀仁@华容南@华容@红寺堡北@黑山北@黄石北@贺胜桥东@黄松甸@汉寿@衡山@虎什哈@惠山@红山@汉寿南@含山南@红寺堡@红砂岘@衡山西@桓台@荷塘@黑台@黄桶北@海坨子@黑旺@徽县@红星@红兴隆@红岘台@换新天@滑浚@合阳@海晏@红彦@合阳北@河源北@海阳北@汉阴@槐荫@花园口@淮阳南@黄羊滩@花园@黄羊镇@' \
# str = '霍州东@黄州@壶镇@化州@霍州@惠州西@巨宝@靖边@金宝屯@晋城北@交城@建昌@加查@泾川@碱厂@鄄城@旌德@峻德@井店@江都@尖峰@鸡冠山@金沟屯@精河@金河@锦河@江华@建湖@纪家沟@锦界@姜家@金坑@金口河南@将乐@芨岭@九郎山@江门东@角美@佳木斯西@莒南@莒南北@济宁北@济宁东@建宁南@建瓯东@建瓯@建瓯西@金普@建桥@江桥@句容西@九三@金山@建始@建水@' \
# str = '稷山@吉舒@建设@甲山@京山@嘉善@嘉善南@界首市@江所田@金山屯@吉水西@景泰@金塔@吉文@嘉祥北@泾县@莒县@嘉祥@郏县@夹心子@揭阳@建阳@姜堰@江油北@巨野北@揭阳机场@揭阳南@江永@江源@靖远@缙云@济源@金月湾@靖远西@缙云西@胶州北@焦作东@晋中@靖州@景州@胶州@旧庄窝@金杖子@康城@宽甸@克东@昆都仑召@库尔木依@开江@喀喇其@开平南@口前@奎山@葵潭@康熙岭@克一河@开远南@昆玉@冷坝@来宾@老边@灵璧@寮步@洛川东@罗城@乐昌@黎城@临城@蓝村@乐东@林东@乐都@梁底下@六道河子@鲁番@落垡@来凤@龙丰@禄丰南@老府@兰岗@龙骨甸@临高南@芦沟@龙沟@临海@凌海@拉哈@林海@滦河@临海南@凌海南@龙华@滦河沿@六合镇@罗江东@亮甲店@刘家河@廉江@罗江@柳江@两家@李家@龙江@莲江口@利津南@李家坪@厉家寨@林口南@路口铺@老莱@龙里北@沥林北@兰陵北@醴陵东@临澧@零陵@陆良@卢龙@喇嘛甸@里木店@洛门@芦庙@龙南@龙南东@六盘水东@落坡岭@六盘山@乐平市@洛浦@临清@礼泉南@' \
# str = '龙泉市@礼泉@临泉@乐山北@冷水江东@连山关@流水沟@灵石@露水河@罗山@涟水@龙市@梁山@鲁山@娄山关南@柳树屯@李石寨@龙山镇@梨树镇@轮台@龙塘坝@濑湍@龙塘镇@李旺@狼尾山@灵武@莱芜西@岚县@朗县@芦溪@临湘@滦县@林西@朗乡@郎溪南@莱西南@莱阳@凌源东@临沂东@老营@临邑@临颍@龙游南@龙游@林源@鹿寨北@临淄北@临泽@龙爪沟@雷州@来舟@鹿寨@拉鲊@六枝南@临泽南@马鞍山@毛坝@毛坝关@明城@毛陈@渑池@庙城@茅草坪@猛洞河@磨刀石@民丰@明港@明港东@马皇@墨江@闵集@马兰@汨罗东@民乐@马莲河@茅岭@庙岭@米林@麻柳@马林@茂林@马龙@木里图@汨罗@玛纳斯湖@牟平@民权北@马桥河@闽清@眉山@明水河@蒙山@麻山@马踏@眉县东@美溪@麻阳@米易东@麦园@麻阳西@庙庄@米脂@明珠@南博山@牛车河@宁城@南仇@南城司@宁都@宁洱@南芬北@南观村@南宫东@南关岭@宁国@南河川@南华@宁化@内黄@泥河子@内江东@宁家@能家@南靖@南江口@南口前@南朗@奈林皋@乃林@南陵@尼勒克@宁明@南木@南堡北@南桥@南台@南头@南屯@乃托@南湾子@泥溪@宁县@内乡@牛心台@宁乡西@娘子关@南漳@南召@南杂木@蓬安@平安@磐安南@普安县@平安驿@平安镇@磐安镇@屏边@平坝南@蒲城@裴德@普定@偏店@瓢儿屯@平岗@平果@平关@盘关@徘徊北@平河口@平湖@潘家店@皮口南@皮口@偏岭@屏南@平南南@朋普@彭山北@蒲石@彭山@彭水@屏山@平社@盘山@坪上@平台@平田@葡萄菁@平旺@平型关@蓬溪@平遥@' \
# str = '彭阳@鄱阳@平洋@平邑@平原堡@平原@平峪@平庄北@邳州东@平庄@邳州@泡子@平庄南@乾安@迁安@秦安@庆城@蕲春南@祁东北@青岛机场@祁东@青堆@庆丰@曲阜南@奇峰塔@清河门北@千河@齐河@清河门@渠旧@潜江@曲江@全椒@秦家@祁家堡@清涧县@秦家庄@七里河@渠黎@秦岭@青莲@青龙@青龙山@祁门@' \
# str = '且末@前磨头@清水北@青神@岐山@前山@确山@清水@青山@清水县@戚墅堰@青田@桥头@犍为@前卫@前苇塘@祁县东@黔西@祁县@乾县@青县@桥西@旗下营南@旗下营@泉阳@千阳@祁阳@沁阳@祁阳北@七营@庆阳山@清原@青州市北@钦州@曲子@青州市@棋子湾@仁布@瑞昌@瑞昌西@如皋@如皋南@容桂@榕江@任丘@融水@热水@乳山@容县@饶阳@汝阳@绕阳河@汝州@石坝@上板城@施秉@上板城南@石城东@商城@舒城@遂昌@顺昌@' \
# str = '神池@石城@山城镇@山丹@山丹马场@绥德@顺德@三道湖@水洞@商都@四道湾@三都县@顺德学院@胜芳@四方台@水富@三关口@桑根达来@上高镇@沙海@上杭@蜀河@松河@沙河口@赛汗塔拉@' \
# str = '泗洪@沙后所@双河市@四会@三河县@四合永@三汇镇@双河镇@三合庄@畲江北@沈家河@双吉@尚家@三江口@司家岭@沙井西@松江镇@三井子@十家子@三家寨@什里店@疏勒@舍力虎@疏勒河@双辽@石岭@石磷@石龙@萨拉齐@索伦@沙岭子@石门县@神木西@山南@肃宁@神农架@苏尼特左旗@双牌@遂平@沙坡头@商丘东@石桥@沈丘@商丘南@水泉@石人@桑日@狮山北@三水北@松山湖北@鄯善@松树@石山@首山@' \
# str = '三水@狮山@泗水@山市@三十家@三水南@泗水南@三十里堡@松树镇@双水镇@索图罕@石梯@三堂集@神头@石头@沙沱@上万@沙湾南@沙湾市@孙吴@歙县北@遂溪@石岘@寿县@沙县@始兴@随县@歙县@泗县@水茜@上西铺@石峡子@寿阳@泗阳@沭阳@松阳@水洋@三阳@射阳@双洋@绥阳@松原北@' \
# str = '邵阳北@三阳川@上腰墩@三营@山阴@三义井@上虞南@三源浦@上园@三原@上虞@邵阳西@绥中北@深圳机场北@嵊州北@孙镇@神州@桑植@深州@肃州@松滋@十字门@师宗@苏州园区@苏州新区@台安@台安南@通安驿@桐柏@太仓@桃村北@桐城东@铁厂沟@铁厂@郯城@桐城@桐城南@太仓南@铁刹山@桃村@田东北@田东@天岗@太谷东@铁干里克@土贵乌拉@通沟@太谷西@太和北@太和东@唐河@唐海南@通化县@团结@谭家井@唐家湾@统军庄@铜陵北@吐列毛杜@图里河@亭亮@田林@天门@太姥山@土牧尔台@土门子@洮南@太平川@太平镇@台前@图强@天桥岭@土桥子@甜水堡@汤山城@台山@桃山@唐山西@天台山@通途@通渭@田心东@藤县@同心@桐乡@田阳@天义@汤阴@驼腰岭@太阳山@桃源@汤原@通远堡西@塔崖驿@滕州@天镇@天祝@天柱山@武安@文安@万安县@王安镇@吴堡@五叉沟@吴川@温春@五大连池@文登东@文登@五道沟@五道河@文地@卫东@望都@武当山西@乌尔旗汗@潍坊北@五府山@王府@湾沟@吴官田@威虎岭北@威海北@芜湖北@芜湖南@卫辉南@卫辉@吴家川@渭津@午汲@威箐@魏家泉@倭肯@五龙背@五龙背东@瓦拉干@五莲@卧龙寺@乌兰木图@卧里屯@望牛墩@乌奴耳@万宁@万年@渭南南@渭南镇@吴桥@巫山@文水@巍山@武山@瓦石峡@魏善庄@五通@王瞳@五台山@王团庄@无为@瓦屋山@五五@武乡东@威信@武乡@闻喜@卫星@无锡新区@王杨@武义北@武义@瓦窑田@五原@湾仔@湾仔北@苇子沟@韦庄@五寨@武陟@湾沚南@魏杖子@微子镇@兴安@新安@新安县@新保安@下板城@西八里@许昌北@项城@小村@新绰源@下城子@喜德@小得江@西大庙@小董@小东@西渡@喜德西@襄汾@信丰@襄汾西@信丰西@新干@孝感@新干东@兴国西@夏格庄@西岗子@宣化北@西湖东@新和@宣和@香河@襄河@斜河涧@新华屯@新华@新化@宣化@西华@小河沿@下花园@小河镇@徐家店@峡江@新绛@仙居南@许家屯@兴凯@小榄@香兰@新李@西柳@西林@新林@新立屯@兴隆县西@西麻山@下马塘@孝南@咸宁北@咸宁东@兴宁@咸宁@兴平@西平@新坪田@西平西@新邱@新青@兴泉堡@仙人桥@小寺沟@夏石@浠水@杏树@下社@徐水@浠水南@杏树屯@许三湾@响水县@邢台@湘潭北@仙桃西@下台子@小湾东@徐闻@新窝铺@西乌旗@修武@' \
# str = '修武西@新县@息县@湘乡@萧县@新乡南@新兴县@西小召@小西庄@向阳@旬阳北@咸阳北@襄垣东@兴业@小雨谷@新沂@小月旧@新沂南@仙游@小扬气@襄垣@夏邑县@新友谊@新阳镇@新帐房@悬钟@汐子@西哲里木@新杖子@永安@永安乡@盐边@元宝山@羊草@永城北@秧草地@禹城东@盐城大丰@砚川@盐池@阳岔@应城@宜城@郓城@晏城@禹城@阳澄湖@' \
# str = '阳城@迎春@雁翅@云彩岭@虞城县@营城子@于都北@英德@云东海@尹地@永定@阳东@园墩@永福南@阳高@杨岗@雨格@阳高南@阳谷@友好@沿河城@洋河@岩会@羊臼河@元江@营街@永嘉@余江@岳家井@云居寺@燕家庄@永康@英库勒@银浪@运粮河@伊拉哈@尉犁@鄢陵@伊林@月亮田@义马@阳明堡@云梦@伊敏@一面山@沂南@云南驿@银瓶@营盘水@乐清东@永庆@杨桥@源迁@玉泉镇@永仁@颍上北@野三关@榆树沟@玉石@阳朔@永寿@云山@窑上@玉舍@沂水@颍上@偃师@' \
# str = '月山@杨树岭@雁石南@野三坡@榆社西@永寿西@鹰手营子@源潭@于田@玉田南@伊通@牙屯堡@烟筒屯@烟台西@羊尾哨@野象谷@阳西@云县@阳信@应县@攸县@永修@攸县南@洋县西@义县西@云阳@酉阳@弋阳@余姚@余姚北@阳邑@鸭园@杨源@鸳鸯镇@燕子砭@宜州@银盏@仪征@耀州@禹州@迤资@羊者窝@杨杖子@镇安@治安@招柏@张百湾@子长@赵城@枝城@邹城@诸城@章党@肇东@照福铺@准格尔@章古台@赵光@政和@中和@织金北@枝江北@钟家村@紫荆关@朱家沟@周家屯@褚家湾@仲恺@曾口@张兰@珠琳@枣林@扎鲁特@樟木头东@樟木头@扎囊@中宁东@' \
# str = '中宁@周宁@中宁南@邹平@镇平@漳浦@张桥@枣强@庄桥@朱日和@中山北@樟树东@钟山@昭山@钟山西@支提山@珠窝@张维屯@彰武@漳县@资溪@棕溪@镇西@钟祥@张辛@' \
# str = '正镶白旗@遵义南@竹园@枣庄东@卓资东@子洲@涿州@中寨@壮志@咋子@卓资山@安溪东@保山@北滩@白银南@茶卡@德化@大田北@富阳西@革居@古路@花博山@黄水@杭州西@江北机场@金阳@靖远北@绛帐@珞璜东@龙兴@明溪@南安北@南彭@平川西@秦王川@水土@三元西@统景@桐庐东@西阳村@漾濞@永春@银花@迎龙@永平县@越西'
# str = '阿勒泰@兴义@安康@阿克苏@鞍山@安庆@黄果树@安阳@包头@蚌埠@北海@邦达@保山@广州@常德@郑州@长春@朝阳@酒泉@赤峰@长治@重庆@大连@长沙@成都@常州@大同@达县 @丹东@东莞@迪庆@大连@大理市@敦煌@东营@东胜@大庸@大足@恩施@延安@福州@绥芬河@阜阳@佛山@富蕴@广汉@格尔木@广元@海口@黑河@呼和浩特@合肥@杭州@海拉尔@乌兰浩特@哈密@衡阳@哈尔滨@韶关@舟山@和田@惠州@黄岩@汉中@银川@且末@庆阳@景德镇@嘉峪关@井冈山@景洪@吉林@九江@晋江@佳木斯@济宁@锦州@衢州@九寨沟@库车@喀什@南昌@昆明@吉安@赣州@库尔勒@克拉玛依@贵阳@桂林@龙岩@兰州@丽江@永州@临沧@泸西@庐山@拉萨@拉萨@洛阳@连云港@临沂@兰州@柳州@南竿@泸州@牡丹江@绵阳@梅县@南充@北京南苑@齐齐哈尔@宁波@南京@南宁@南阳@南通@满洲里@北京@蓬莱@上海@攀枝花@如皋@上海@沈阳@山海关@秦皇岛@沙市@西安@石家庄@汕头@思茅 @三亚@商州@苏州@深圳@青岛@塔城@铜仁@通辽@济南@通化@天津@屯溪@太原@乌鲁木齐@玉林@潍坊@威海@芜湖@温州@武汉@武汉@武夷山@无锡@梧州@万县@兴城@襄樊@西昌@锡林浩特@兴宁县@西安@厦门@西宁@邢台@徐州@宜宾@宜昌@伊宁@义乌@宜兰@延吉@烟台@盐城@元谋@昭通@兰州@湛江@珠海@遵义'
# str = '阿勒泰机场@兴义机场@安康机场@阿克苏机场@鞍山机场@安庆机场@黄果树机场@安阳机场@包头机场@蚌埠机场@北海机场@邦达机场@保山机场@广州机场@常德机场@郑州机场@长春机场@朝阳机场@酒泉机场@赤峰机场@长治机场@重庆机场@大长山倒@长沙机场@成都机场@常州机场@大同机场@达县机场@丹东机场@东莞机场@迪庆机场@大连机场@大理市机场@敦煌机场@东营机场@东胜机场@大庸机场@大足机场@恩施机场@延安机场@福州机场@绥芬河机场@阜阳机场@佛山机场@富蕴机场@广汉机场@格尔木机场@广元机场@海口机场@黑河机场@呼和浩特机场@合肥机场@杭州萧山国际机场@海拉尔机场@乌兰浩特机场@哈密机场@衡阳机场@哈尔滨机场@韶关机场@舟山机场@和田机场@惠州机场@黄岩机场@汉中机场@银川机场@且末机场@庆阳机场@景德镇机场@嘉峪关机场@井冈山机场@景洪机场@吉林机场@九江机场@晋江机场@佳木斯机场@济宁机场@锦州机场@衢州@九寨黄龙机场@库车机场@喀什机场@南昌机场@昆明机场@吉安机场@赣州机场@库尔勒机场@克拉玛依机场@贵阳机场@桂林机场@龙岩机场@' \
str = '兰州机场@丽江机场@永州零陵机场@临沧机场@德宏芒市机场@庐山机场@拉萨机场@拉萨机场@洛阳机场@连云港机场@临沂机场@兰州东机场@柳州机场@南竿机场#马祖岛@泸州机场@牡丹江机场@绵阳机场@梅县机场@南充机场@北京南苑机场@齐齐哈尔机场@宁波机场@南京机场@南宁机场@南阳机场@南通机场@满洲里机场@北京首都国际机场@蓬莱机场@上海浦东国际机场@攀枝花机场@如皋机场@上海虹桥机场@沈阳机场@山海关机场@秦皇岛机场@沙市机场@西安机场@石家庄机场@汕头机场@思茅机场@三亚机场@商州机场@苏州机场@深圳机场@青岛机场@塔城机场@铜仁机场@通辽机场@济南机场@通化机场@天津机场@屯溪机场@太原机场@乌鲁木齐机场@玉林机场@潍坊机场@威海机场@芜湖机场@温州机场@武汉机场@武汉机场@武夷山机场@无锡机场@梧州机场@万县机场@兴城机场@襄樊机场@西昌机场@锡林浩特机场@兴宁县机场@西安咸阳机场@厦门机场@西宁机场@邢台机场@徐州机场@宜宾机场@宜昌机场@伊宁机场@义乌机场@宜兰机场@延吉机场@烟台机场@盐城机场@元谋机场@昭通机场@中川机场@湛江机场@珠海机场@遵义机场'
strs = str.split('@')
for i in range(0, len(strs)):
    # print(strs[i])
    get_data(strs[i])
