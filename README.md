# Java Calculator Application

## Overview
A simple desktop calculator built with Java Swing featuring a modern dark theme interface and basic arithmetic operations.

## Key Features
- Basic operations: +, -, *, /, %
- Dark theme with color-coded buttons
- Special functions: sign change (±), percentage (%), clear options
- Error handling for division by zero
- Decimal number support

## How to Run
1. Ensure Java JDK 8+ is installed
2. Compile: `javac com/mycompany/java_calulator_app/CalculatorApp.java`
3. Run: `java com.mycompany.java_calulator_app.CalculatorApp`

## Technical Highlights
- Built with Java Swing for the UI
- Uses GridLayout for button arrangement (5×4 grid)
- Custom color scheme with dark background (RGB 50,50,50)
- Color-coded buttons for intuitive use
- Handles consecutive operations correctly
- Formats results to remove unnecessary decimal places

## Implementation
- Main class: `CalculatorApp` extends JFrame
- Event handling via `ButtonClickListener` inner class
- Proper operation sequence and calculation logic
- Styled UI components with consistent design

