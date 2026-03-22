package com.macaosoftware.nav3playground.moduleA.ui

import com.macaosoftware.nav3playground.moduleA.arch.ModuleABlockScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import kotlin.random.Random

@Inject
@SingleIn(ModuleABlockScope::class)
class ModuleASharedDataManager(val random: Int = Random(100_000).nextInt())