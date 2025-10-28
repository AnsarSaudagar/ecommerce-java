# Ecommerce Spring Boot Application

## Auto-Refresh Configuration

This project is now configured with Spring Boot DevTools for automatic application restart and hot reloading during development.

### 🚀 Quick Start

#### Development Mode (with Auto-Refresh)
```bash
./start.sh
```
This will start the application with auto-refresh enabled. Any changes to Java files will automatically restart the application.

#### Production Mode (without Auto-Refresh)
```bash
./start-prod.sh
```
This will start the application in production mode without auto-refresh.

### 🔧 Auto-Refresh Features

1. **Automatic Restart**: Application restarts automatically when you change Java files
2. **Live Reload**: Browser automatically refreshes when static resources change
3. **Fast Restart**: Only restarts the application context, not the entire JVM
4. **Excluded Paths**: Static resources, templates, and Maven files don't trigger restarts

### 📁 What Triggers Auto-Refresh

- ✅ Changes to Java files in `src/main/java/`
- ✅ Changes to properties files in `src/main/resources/`
- ❌ Changes to static files (`static/`, `public/`, `resources/`)
- ❌ Changes to templates (`templates/`)
- ❌ Maven files (`pom.xml`, `target/`)

### 🛠️ Configuration Files

- `application-dev.properties`: Development configuration with auto-refresh
- `application-prod.properties`: Production configuration without auto-refresh
- `application.properties`: Base configuration

### 🔍 Troubleshooting

If auto-refresh isn't working:

1. Make sure you're using the development profile:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   ```

2. Check that DevTools dependency is included in `pom.xml`

3. Verify the application is running with dev profile by checking the logs

### 📝 Notes

- Auto-refresh only works in development mode
- The application will restart automatically when you save Java files
- Browser will refresh automatically for static content changes
- Database connections are maintained during restart
