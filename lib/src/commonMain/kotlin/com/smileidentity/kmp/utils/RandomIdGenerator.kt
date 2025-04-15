package com.smileidentity.kmp.utils

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class) fun randomId(prefix: String): String = "$prefix-${Uuid.random()}"

fun randomUserId(): String = randomId("user")

fun randomJobId(): String = randomId("job")
