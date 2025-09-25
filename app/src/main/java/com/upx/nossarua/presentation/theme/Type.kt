package com.upx.nossarua.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Custom Material 3 Typography for the application.
 *
 * This typography set defines various text styles to be used throughout the app,
 * ensuring consistency and adherence to Material Design principles.
 *
 * Usage guide:
 * 1. Display (Large, Medium, Small):
 *    - Use for the largest text on the screen
 *    - Typically for short, important text or numerals
 *    - Examples: App title on main screen, large numbers in a countdown timer, featured content headers
 *
 * 2. Headline (Large, Medium, Small):
 *    - Use for high-emphasis text that's smaller than Display styles
 *    - Examples: Screen titles, important section headers
 *
 * 3. Title (Large, Medium, Small):
 *    - Use for medium-emphasis text that's smaller than Headline styles
 *    - Examples: Less important section headers, dialog titles
 *
 * 4. Body (Large, Medium, Small):
 *    - Use for the main text content of your app
 *    - Examples: Paragraph text, list items, description text
 *
 * 5. Label (Large, Medium, Small):
 *    - Use for text in components or very small text in the content body
 *    - Examples: Text in buttons, text field labels, captions or overlines
 *
 * To use these styles in your composables:
 * Text(
 *     text = "Your text here",
 *     style = MaterialTheme.typography.bodyLarge
 * )
 *
 * Ensure to wrap your app's root composable with the custom theme that includes this typography:
 * YourAppTheme {
 *     // Your app content
 * }
 */

val font = FontFamily.Default

val customTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
        color = DarkText
    ),
    displayMedium = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp,
        color = DarkText
    ),
    displaySmall = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp,
        color = DarkText
    ),
    headlineLarge = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp,
        color = DarkText
    ),
    headlineMedium = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp,
        color = DarkText
    ),
    headlineSmall = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp,
        color = DarkText
    ),
    titleLarge = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = DarkText
    ),
    titleMedium = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        color = DarkText
    ),
    titleSmall = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        color = DarkText
    ),
    bodyLarge = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = DarkText
    ),
    bodyMedium = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        color = DarkText
    ),
    bodySmall = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
        color = DarkText
    ),
    labelLarge = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        color = DarkText
    ),
    labelMedium = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        color = DarkText
    ),
    labelSmall = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        color = DarkText
    )
)
