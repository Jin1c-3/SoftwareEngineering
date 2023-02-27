import os
import time
from selenium.webdriver.common.keys import Keys

from selenium.webdriver.common.by import By
from selenium.webdriver.edge.options import Options
from selenium import webdriver
from prettytable import PrettyTable
import warnings

warnings.filterwarnings("ignore")

options = Options()
# options.add_argument("headless")
browser = webdriver.Edge(executable_path='msedgedriver.exe', options=options)

url = "https://www.12306.cn/index/"

browser.get(url)

os.system("cls")
print('''
依次输入始发站、目的地、日期。日期格式为2023-03-08.日期不能太久了。
''')
sfz = input('始发站：')
mdd = input('目的地：')
rq = input('日期：')

date = browser.find_element(By.XPATH, '//div[@class="form-bd"]//input[@id="train_date"]')
date.click()
date.clear()
date.send_keys(rq)

ddd = browser.find_element(By.XPATH, '//div[@class="form-bd"]//input[@id="toStationText"]')
ddd.click()
ddd.send_keys(mdd)
ddd.send_keys(Keys.ENTER)
# browser.find_element(By.ID, 'search-input').click()

cfd = browser.find_element(By.XPATH, '//div[@class="form-bd"]//input[@id="fromStationText"]')
cfd.click()
cfd.send_keys(sfz)
cfd.send_keys(Keys.ENTER)
browser.find_element(By.ID, 'search-input').click()

time.sleep(1)
browser.find_element(By.ID, "search_one").click()

num = browser.window_handles  # 获取当前页的所有句柄
browser.switch_to.window(num[1])  # 跳到新的标签页；因为当前只有一个主页面和新的标签页，所以num[1]表示新标签    页，num[0]表示主页面

che_ci = browser.find_elements(By.XPATH, '//td/*[1]/*[1]//a')
chu_fa_zhan = browser.find_elements(By.XPATH, '//td/*[1]/*[2]/*[1]')
dao_da_zhan = browser.find_elements(By.XPATH, '//td/*[1]/*[2]/*[2]')
chu_fa_time = browser.find_elements(By.XPATH, '//td/*[1]/*[3]/*[1]')
dao_da_time = browser.find_elements(By.XPATH, '//td/*[1]/*[3]/*[2]')

i = 1
while i <= len(che_ci):
    browser.find_element(By.XPATH, f'//tr[{i}]//td[3]').click()
    time.sleep(1)
    i += 2

table = PrettyTable()
table.field_names = ['车次', '始发站', '目的地', '出发时间', '到达时间']
for a, b, c, d, e in zip(che_ci, chu_fa_zhan, dao_da_zhan, chu_fa_time, dao_da_time):
    table.add_row([a.text, b.text, c.text, d.text, e.text])
print(table)
os.system("pause")
