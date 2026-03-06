package com.macaosoftware.nav3playground.moduleB.arch

import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import kotlin.random.Random

@Inject
@SingleIn(ModuleBBlockScope::class)
class ModuleBSharedDataManager(val random: Int = Random(100_000).nextInt())