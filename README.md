# 🍽️ Meal Categories App - MVP (Model-View-Presenter)

This project implements **Model-View-Presenter (MVP)** architecture in an Android app.  
It fetches meal categories from **TheMealDB API** and allows users to **add/remove favorites**.

---

## 📌 Features
- ✅ Fetch meal categories from **TheMealDB API**
- ✅ Display categories in **RecyclerView**
- ✅ Add categories to **Favorites (Room Database)**
- ✅ Remove categories from Favorites
- ✅ Implements **MVP Architecture** (with Presenter)

  ---

## ✅ Benefits of MVP
- **Better separation of concerns** (UI logic in `View`, business logic in `Presenter`).
- **Easier to test** (Presenter can be tested independently).
- **Less bloated Activities**.

## ❌ Drawbacks of MVP
- **Presenter needs manual lifecycle management**.
- **More boilerplate code**.

---

---

## 🚀 Installation Guide
### **🔹 Clone the Repository**

```sh
git clone https://github.com/your-repo/TestMVP.git
cd TestMVP
```
🔹 Open in Android Studio
Open Android Studio
Click "Open an Existing Project"
Select TestMVP
Sync Gradle and Run! 🚀

⚙️ Tech Stack
Kotlin 🟡
Retrofit (API requests) 🌐
Room Database (Local storage) 🏡
MVP Pattern (Decouples UI from Logic)

📌 API Used
This project uses TheMealDB API to fetch meal categories.
Base URL:
```sh
https://www.themealdb.com/api/json/v1/1/
```
