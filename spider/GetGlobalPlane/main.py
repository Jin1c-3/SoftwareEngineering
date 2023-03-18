import requests
from bs4 import BeautifulSoup
import re
import logging

# 创建一个logger
logger = logging.getLogger("mylog")
# Log等级总开关
logger.setLevel(level=logging.DEBUG)

# 获取文件日志句柄并设置日志级别，第二层过滤
handler = logging.FileHandler("log.txt")
handler.setLevel(logging.INFO)

# 生成并设置文件日志格式，其中name为上面设置的mylog
formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
handler.setFormatter(formatter)

# 获取流句柄并设置日志级别，第二层过滤
console = logging.StreamHandler()
console.setLevel(logging.WARNING)

# 为logger对象添加句柄
logger.addHandler(handler)
logger.addHandler(console)

# 定义我们需要爬取的网站地址
url = 'http://www.yicang.com'

# 定义我们需要爬取的网站主连接
urlmain = url + '/airports.html'
# 获取主连接内容
req = requests.get(urlmain, {
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36'})
req.encoding = 'utf-8'
soup = BeautifulSoup(req.text, 'lxml')

# 查询所有a标签里面的url
xmlmain = soup.find_all('a')

# 定义一个数组，存储所有国家对于的url
gj = []

# 遍历'a'标签获取国家对于的url
for portlist in xmlmain:
    # 根据规则判断url是否包含'/airports/'，筛选我们需要的url
    if '/airports/' in portlist['href']:
        gj.append(portlist['href'])

# 定义一个url存储每个国家的机场信息url
porturllist = []


# 封装获取机场信息的url的方法
def getporturllist(porturl):
    reqgj = requests.get(porturl, {
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36'})
    reqgj.encoding = 'utf-8'
    soupgj = BeautifulSoup(reqgj.text, 'lxml')
    # 我们只需要找到详情信息的元素
    xmlli = soupgj.find_all('li', class_='listnr6')
    # 遍历详情的元素内容
    for portdetailurlxml in xmlli:
        # 获取详情对应的url
        portdetailurl = portdetailurlxml.find('a')['href']
        # 判断这个url是否符合我们的规则
        if '/AirPortsinfo/' in portdetailurl:
            # 将url信息存储到porturllist里面
            porturllist.append(portdetailurl)


# 遍历每个国家的url获取这个国家所有机场的url
for portlist in gj:
    # 这里需要再次初始化一次每个国家的机场信息url列表
    porturllist = []
    # 拼接出这个国家机场列表对应的url
    urlgj = url + portlist
    getporturllist(urlgj)

    # 获取国家机场列表里面的信息，判断是否有分页
    reqgj = requests.get(urlgj, {
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36'})
    reqgj.encoding = 'utf-8'
    soupgj = BeautifulSoup(reqgj.text, 'lxml')
    # 获取分页对应的div元素
    xmlpage = soupgj.find_all('div', class_='ports_page')
    for pageinfo in xmlpage:
        # 获取最后页的元素
        xmlpagelast = pageinfo.find_all('li', class_='last')
        for pagelast in xmlpagelast:
            # 得到最后页的url
            pagelasturl = pagelast.find('a')['href']
            # 判断url是否符合规则
            if 'BaseAirports_page=' in pagelasturl:
                # 获取最后页的页码
                pagecountstr = pagelasturl.split('=')[-1]
                pagecount = int(pagecountstr) + 1
                # 循环这个页码，然后拼接出所有分页的机场列表
                for i in range(2, pagecount):
                    # 获取分页列表里面每个机场详情的url
                    pageurl = urlgj + '?BaseAirports_page=' + str(i)
                    getporturllist(pageurl)

    # 遍历这个国家所有机场详情url
    for portdetail in porturllist:
        try:
            porturl = url + portdetail
            reqport = requests.get(porturl, {
                'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36'})
            reqport.encoding = 'utf-8'
            soupport = BeautifulSoup(reqport.text, 'lxml')
            # 筛选class为shippings_showT的dl元素
            xmldiv = soupport.find_all('dl', class_='shippings_showT')
            # dl元素下的所有dd标签内容存储到此数组
            dlArry = []
            for divport in xmldiv:
                ddArry = []
                for dd in divport.find_all('dd'):
                    ddArry.append(dd.text)
                dlArry.append(ddArry)
            text = ''
            # 将存储的机场详情拼接输入到控制台，并存储到日志文件
            for ddArry in dlArry:
                for item in ddArry:
                    text = text + item + ','
            print(text)
            logger.info(text)
        except Exception as ex:
            print(str(ex))