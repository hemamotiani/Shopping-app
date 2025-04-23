# Shopping-app

This is a Shopping List mobile application developed in Android Studio using **Jetpack Compose** as part of the AIT - Mobile Software Development course assignment.

The app helps users manage their shopping by letting them add, edit, mark, and remove items from a shopping list. It practices key Android concepts including:
- Compose UI
- Room database (persistence)
- Navigation
- Dialogs
- LazyColumn
- State management

---

## 📲 Features

- 🖼️ **Splash Screen** with a custom logo (displays for 3 seconds)
- 📋 **Shopping List Screen**:
  - View a scrollable list of shopping items with:
    - Icon (based on category)
    - Checkbox for "bought" status
    - Item name and optional description
- ➕ **Add New Item Dialog**:
  - Choose category from dropdown (e.g., Food, Electronic, Book)
  - Input item name, description, estimated price
  - Save and view items instantly
- ✏️ **Edit Existing Items**
- ❌ **Delete Items** individually or all at once
- 💾 **Persistence using Room database**
- 🌟 **Custom Feature** (e.g., item details screen, price-based sorting, or category filter)

---

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVVM (ViewModel, Repository, DAO)
- **Database**: Room (with Flow & suspend functions)
- **Navigation**: Jetpack Navigation Compose
- **IDE**: Android Studio

---

## 🚀 How to Run the App

1. **Clone the Project or Download ZIP**  
   Unzip the provided archive and open it in **Android Studio**.

2. **Open the Project**  
   - Go to: `File > Open` → Select the root folder of the project.

3. **Sync Gradle**  
   Let Gradle sync all dependencies automatically (you might need an internet connection the first time).

4. **Run the App**  
   - Connect your Android device (with USB debugging on) or open an emulator.
   - Hit the green ▶️ "Run" button or press `Shift + F10`.
