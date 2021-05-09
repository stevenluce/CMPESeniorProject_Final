import urllib.request
import requests
import json
#import csv #don't need?
#========================================================
with urllib.request.urlopen("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minlatitude=32&minlongitude=-125&maxlatitude=41&maxlongitude=-113&minmagnitude=3&starttime=now-2days") as url:
    eq = json.loads(url.read().decode())
    print(eq)

jsoneq = json.dumps(eq)
f = open("Earthquakes.json","w")
f.write(jsoneq)
f.close()
#========================================================
with urllib.request.urlopen("https://www.fire.ca.gov/umbraco/api/IncidentApi/List?inactive=false") as url:
    fire = json.loads(url.read().decode())
    print(fire)

jsonfire = json.dumps(fire)
f = open("Fires.json","w")
f.write(jsonfire)
f.close()
#========================================================
url = ('https://newsapi.org/v2/everything?'
       'q=California&'
       'from=2021-04-24&'
       'sortBy=popularity&'
       'apiKey=a74f27711d1a462f82e679720bb8376c')
response = requests.get(url)
print (response.json())
news = response.json()

jsonnews = json.dumps(news)
f = open("News.json","w")
f.write(jsonnews)
f.close()
