import sys
import requests
from bs4 import BeautifulSoup
import csv

def scrape_data(url):
    response = requests.get(url)
    soup = BeautifulSoup(response.text, 'html.parser')

    # Find the plain text data on the webpage
    # Replace 'your_data_tag_here' and 'your_attribute_here' with appropriate tag and attribute
    data_tag = soup.find('your_data_tag_here', attrs={'your_attribute_here': 'your_attribute_value_here'})
    data_text = data_tag.get_text()
    
    return data_text

def save_data_to_csv(data, file_path):
    with open(file_path, 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
        for line in data.split('\n'):
            writer.writerow([line])

def main():
    url = sys.argv[1]
    file_path = '../data/scraped_data_1.csv'
    data = scrape_data(url)
    save_data_to_csv(data, file_path)

if __name__ == '__main__':
    main()
