package com.macaosoftware.nav3playground.moduleB.ui

import com.macaosoftware.nav3playground.moduleB.arch.ModuleBBlockScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import kotlin.random.Random

@Inject
@SingleIn(ModuleBBlockScope::class)
class ModuleBSharedDataManager(val random: Int = Random(100_000).nextInt())