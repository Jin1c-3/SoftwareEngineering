import requests
import os

png_list = ['3U', '8L', '9C', '9H', 'A6', 'AQ', 'BK', 'CA', 'CF', 'CN', 'CZ', 'DR', 'DZ', 'EU', 'FM', 'FU', 'G5', 'GI',
            'GJ', 'GS', 'GT', 'GX', 'GY', 'HO', 'HU', 'JD', 'JR', 'KN', 'KY', 'LT', 'MF', 'MU', 'NS', 'O3', 'OQ', 'PN',
            'QW', 'RY', 'SC', 'TV', 'UQ', 'Y8', 'YG', 'ZH']

url = 'https://pic.c-ctrip.com/AssetCatalog/airline/48/{plane}.png?v=2'

for png in png_list:
    response = requests.get(url.format(plane=png))
    if response.status_code == 200:
        if not os.path.exists('png'):
            os.mkdir('png')
        with open('png/{plane}.png'.format(plane=png), 'wb') as f:
            f.write(response.content)
            print('下载成功')
    else:
        print(png + '===下载失败')
