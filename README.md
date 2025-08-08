# Convertly - Unit Converter REST API

Convertly is a clean, organized REST API for converting units across different measurement categories such as temperature, length, weight, and time.

---

## Features

### Core Features
- **POST /convert**  
  Convert a value from one unit to another.  
  Accepts JSON payload:
  ```json
  {
    "category": "temperature",
    "fromUnit": "celsius",
    "toUnit": "fahrenheit",
    "value": 25
  }
  ```
  Returns JSON with:
    - Converted result
    - Explanation/formula of conversion
    - Status message

- **GET /categories**  
  Returns all available unit categories:
  ```json
  ["temperature", "length", "weight", "time"]
  ```

- **GET /units?category={category}**  
  Returns supported units for a given category. Example:  
  `/units?category=temperature`
  ```json
  ["celsius", "fahrenheit", "kelvin"]
  ```

- **GET /sample-payload**  
  Returns a sample request body for testing the `/convert` endpoint.

- **GET /health**  
  Simple health check endpoint returning:
  ```json
  { "status": "Unit Converter API is up and running" }
  ```

---



---

## Supported Categories and Units

| Category    | Units                                  |
|-------------|---------------------------------------|
| Temperature | Celsius, Fahrenheit, Kelvin           |
| Length      | Meter, Kilometer, Mile, Inch, Foot    |
| Weight      | Gram, Kilogram, Pound, Ounce          |
| Time        | Second, Minute, Hour, Day              |

---


## How to Run

1. Clone the repository
   ```bash
   git clone <repository-url>
   ```

2. Build the project using Maven or your IDE

3. Run the Spring Boot application
   ```bash
   mvn spring-boot:run
   ```

4. Access the API at `http://localhost:8080/swagger-ui/index.html#/`

---

## Example Request

```bash
curl -X POST http://localhost:8080/convert \
  -H "Content-Type: application/json" \
  -d '{
        "category": "temperature",
        "fromUnit": "celsius",
        "toUnit": "fahrenheit",
        "value": 25
      }'
```

Response:

```json
{
  "result": 77.0,
  "formula": "(25.0°C × 9/5) + 32 = 77.0°F",
  "status": "success"
}
```

---

## Error Handling

Invalid inputs such as unsupported units or categories will return HTTP 400 responses with JSON error messages like:

```json
{
  "timestamp": "2025-08-08T14:12:45.123",
  "status": 400,
  "error": "Invalid Unit",
  "message": "Unsupported temperature unit: foo"
}
```

