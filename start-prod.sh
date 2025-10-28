#!/bin/bash

# Ecommerce Spring Boot Application Production Startup Script
echo "Starting Ecommerce Application in Production Mode..."

# Check if MySQL is running
if ! pgrep -x "mysqld" > /dev/null; then
    echo "Warning: MySQL doesn't appear to be running. Please start MySQL first."
    echo "You can start MySQL with: brew services start mysql"
    echo ""
fi

# Clean and compile
echo "Cleaning and compiling project..."
mvn clean compile

if [ $? -ne 0 ]; then
    echo "Compilation failed. Please fix the errors before running the application."
    exit 1
fi

# Start the application in production mode (no auto-refresh)
echo "Starting Spring Boot application in production mode..."
mvn spring-boot:run -Dspring-boot.run.profiles=prod
