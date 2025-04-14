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

The SDK supports **both** local development (`local.properties`) and CI/CD (environment variables)
setups:

### 1. Local Development

Add these to your project's `local.properties` (⚠️ **Never commit this file**):

```properties
SMILE_ID_API_KEY=your-smile-id-api-key
SMILE_ID_ENVIRONMENT=SANDBOX
SMILE_ID_SMILE_LINK=https://your-smile-link
```

### 2. CI/CD & Production

Set these environment variables in your build system:

| Environment Variable   | Description                                                          |
|------------------------|----------------------------------------------------------------------|
| `SMILE_ID_API_KEY`     | Your Smile Identity API key (obtained from Smile ID dashboard)       |
| `SMILE_ID_ENVIRONMENT` | Runtime environment - **SANDBOX** (testing) or **PRODUCTION** (live) |
| `SMILE_ID_SMILE_LINK`  | Unique SmileLink URL provided during partner onboarding              |

> Warning
> Ensure Your local.properties is not checked into version control by adding it into your .gitignore

## 🛠️ Initialization

The library initializes automatically using values from `local.properties`, but you can override
this with manual configuration.

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