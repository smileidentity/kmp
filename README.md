# 🛡️ SMILE ID KMP

A **Kotlin Multiplatform** library for performing SMILE ID's Identity Verification securely across *
*Android** , **iOS** and **Desktop** platforms.

---

## 📦 Installation

Add this to your `libs.versions.toml`:

```toml
[versions]
smile-id-kmp = "latest"

[libraries]
smile-id-kmp = { group = "smile-id-kmp", name = "lib", version.ref = "smile-id-kmp" }
```

## 🔧 Configuration

Add your smile id configuration to your project's `local.properties` file:

```properties
SMILE_ID_API_KEY=your-smile-id-api-key
SMILE_ID_ENVIRONMENT=SANDBOX
SMILE_ID_SMILE_LINK=https://your-smile-link
```

## 🛠️ Initialization

The Library performs self initialization using the values in your local.properties, however, you can
override the same using the following builder patter

```kotlin
SmileIdentity.Builder()
    .setApiKey("your-api-key")
    .setEnvironment(Environment.SANDBOX)
    .build()

```

## 🚀 Usage

Currently, the Library supports on the **Enhanced Document Verification** Workflow. You can consume
this as:

```kotlin
EnhancedDocumentVerification()
```