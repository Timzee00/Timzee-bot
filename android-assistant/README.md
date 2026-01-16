# Timzee Android Assistant (Standalone Skeleton)

This folder contains a standalone Android **skeleton app** for an assistant that can work offline and online. It is **not** a full AI system; instead it provides a complete Android project structure with clear extension points for:

- On-device (offline) reasoning runtimes
- Optional web search augmentation (online)
- Document ingestion (PDF, images, Office docs)
- Accessibility automation (with user consent)
- Voice wake and TTS
- Owner-only unrestricted mode via a passphrase
- Local interaction memory for personalization

## What this includes
- A buildable Android project with Gradle configuration
- Core engine that routes prompts, handles owner mode, and orchestrates tools
- Web search tool with a policy gate for optional online augmentation
- Document processing pipeline interfaces (PDF, image OCR, Word)
- Service stubs for accessibility automation and voice wake
- Local memory store stub for on-device learning

## Important notes
- This is a **starter skeleton** focused on architecture. It does **not** ship a full AI model.
- For offline intelligence, plug in a local on-device runtime and model weights (free/open-source options exist).
- For online augmentation, use an unpaid endpoint or self-hosted search index.
- Accessibility automation must comply with Android policies and explicit user consent.
- "Unrestricted" behavior is not recommended; ship clear user controls and obey platform policies.

## Owner mode
The assistant supports an owner-only passphrase. When the passphrase is detected in a prompt, the assistant enables an "owner mode" with fewer restrictions. Update the passphrase in `OwnerModeGuard`.

## Next steps
1. Implement a real `LocalModelRuntime` using a free on-device runtime.
2. Wire document parsers (PDFBox, Apache POI, platform APIs).
3. Connect `VoiceWakeService` to hotword detection and TTS.
4. Register the accessibility service with a clear user consent flow.
5. Add secure storage for the owner passphrase and user preferences.
6. Replace the memory store stub with on-device embeddings or summaries.
