# Air Quality Data Crawler

Real-time air quality data collection from AirKorea Open API

## 📋 Overview

This system collects air quality data from AirKorea Open API for Daejeon region monitoring stations and saves it in CSV format at regular intervals.

## 🛠 Technology Stack

- **Language**: Java 1.8
- **Build Tool**: Maven
- **Key Libraries**:
  - Jersey Client 2.25.1 (REST API communication)
  - Jsoup 1.13.1 (HTML parsing)
  - OpenCSV 3.8 (CSV file processing)
  - Logback 1.2.3 (Logging)

## 📦 Installation

### 1. Install Java 8

Using SDKMAN (recommended):
```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 8.0.432-zulu
```

### 2. Build Project

```bash
cd air-quality-crawler
mvn clean install
```

## ⚙️ Configuration

### Environment Variables

Create `.env` file in project root:

```bash
AIRKOREA_API_KEY=your-api-key-here
ADMIN_USER=admin
ADMIN_PASSWORD=your-secure-password
DATA_COLLECTION_INTERVAL=3600
```

### application.properties

**⚠️ Warning**: Use environment variables for sensitive data.

```properties
# AirKorea API Configuration
user=${ADMIN_USER}
password=${ADMIN_PASSWORD}
timer=${DATA_COLLECTION_INTERVAL}
```

## 🚀 Usage

```bash
# Run with Maven
mvn exec:java -Dexec.mainClass="app.AppMain"

# Or run JAR file
java -jar target/air-quality-crawler-0.0.1-SNAPSHOT.jar
```

## 📊 Data Structure

### Collected Data
- Station information (`station.csv`)
- Real-time air quality data (`pair_YYYYMMDD.csv`)
- Station list (`getMsrstnList_Daejeon.xml`)

### CSV Format
```csv
stationName,dataTime,pm10Value,pm25Value,so2Value,coValue,o3Value,no2Value
Daejeon Station,2020-07-20 10:00,45,23,0.004,0.4,0.028,0.021
```

## 🔧 Key Features

- ✅ AirKorea API data collection
- ✅ Timer-based periodic collection (default: 1 hour)
- ✅ CSV file storage
- ✅ Logging (Logback)
- ✅ Daejeon region station management

## 📝 API Key Registration

1. Visit [AirKorea Public Data Portal](https://www.data.go.kr)
2. Sign up and log in
3. Search for "한국환경공단_에어코리아_대기오염정보" (Korea Environment Corporation AirKorea Air Pollution Information)
4. Apply for API key
5. Add key to `.env` file

## ⚠️ Important Notes

- Never commit API keys to Git
- `application.properties` is included in `.gitignore`
- Check API rate limits and set appropriate `timer` value

## 🐛 Troubleshooting

### API Call Failure
```
Solution: Verify API key and network connection
```

### CSV File Save Error
```
Solution: Check directory write permissions
```

## 📄 License

MIT License

## 👥 Contributors

- Development: Air Quality Monitoring Team
- Organization: Korea Testing & Research Institute (KTR)
