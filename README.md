# SouthAmericanCurrencyConverter 💱🌎

A Java-based currency converter focused on South American currencies. This system allows users to convert amounts between USD and various South American currencies (plus EUR), using real-time exchange rate data from a public API.

---

## 🎯 Objective

This project was developed as part of a programming challenge to practice:

- Working with external APIs
- User input validation
- Clean code principles: SRP (Single Responsibility), DRY (Don't Repeat Yourself)
- Error handling and robust logic
- Git/GitHub version control and documentation

---

## 🚀 Features

- ✅ Convert currencies both ways (e.g., USD → ARS and ARS → USD)
- 🔄 Live exchange rate data from [ExchangeRate-API](https://www.exchangerate-api.com/)
- 👨‍💻 Strong input validation (invalid input, range checking, empty input, EOF)
- 🧠 Clean code with separated responsibilities (SRP)
- 🧪 Graceful error handling for HTTP and runtime exceptions
- ⏸ Pause after every result or error for better user experience

---

## 🧰 Technologies

- Java 21
- Maven
- [Gson](https://github.com/google/gson) for JSON parsing
- Public exchange rate API
- Git / GitHub

---

<pre> <code> ## 📦 Project Structure ```plaintext SouthAmericanCurrencyConverter/ ├── src/ │ ├── model/ │ │ ├── ConversionOption.java │ │ └── ExchangeResponse.java │ ├── CurrencyConverter.java │ └── ExchangeRateService.java ├── .gitignore └── README.md ``` </code> </pre>



## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/SouthAmericanCurrencyConverter.git
cd SouthAmericanCurrencyConverter

2. Open the project
Use IntelliJ or your preferred Java IDE.

Place your source code inside the src/ directory.

Import the Gson library (via Maven or manually as a .jar file).

3. Set your API Key as an environment variable (on Windows)
powershell
Copy
Edit
setx EXCHANGE_API_KEY "your_api_key_here"
⚠️ Restart IntelliJ or your terminal after setting the variable to ensure it's available.

4. Run the app
Run the CurrencyConverter class as a Java application.

