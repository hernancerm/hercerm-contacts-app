const plugin = require("tailwindcss/plugin");

module.exports = {
  purge: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  darkMode: false, // or 'media' or 'class'
  theme: {
    minHeight: {
      "28": "7rem",
      "32": "8rem",
      "36": "9rem",
      "40": "10rem"
    }
  },
  variants: {
    extend: {
      backgroundColor: ["active"]
    }
  },
  plugins: [
    // Set color of individual borders
    plugin(function({ addUtilities, theme }) {
      const themeColors = theme("colors");
      const individualBorderColors = Object.keys(themeColors).map(
        colorName => ({
          [`.border-b-${colorName}`]: {
            borderBottomColor: themeColors[colorName]
          },
          [`.border-t-${colorName}`]: {
            borderTopColor: themeColors[colorName]
          },
          [`.border-l-${colorName}`]: {
            borderLeftColor: themeColors[colorName]
          },
          [`.border-r-${colorName}`]: {
            borderRightColor: themeColors[colorName]
          }
        })
      );

      addUtilities(individualBorderColors);
    })
  ]
};
