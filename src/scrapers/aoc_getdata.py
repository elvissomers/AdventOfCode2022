from aocd import get_data
import sys

def main():
	year = 2022
	for day in range(1,26):
		file_path = '../../data/scraped_data_'+str(day)+'.txt'
		data = get_data(day=day, year=year)
		with open(file_path, 'w') as txtfile:
			txtfile.write(data)

if __name__ == '__main__':
	main()
