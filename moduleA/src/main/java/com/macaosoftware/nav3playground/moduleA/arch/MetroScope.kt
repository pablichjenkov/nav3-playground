package com.macaosoftware.nav3playground.moduleA.arch

import com.macaosoftware.nav3playground.moduleA.ui.view.ChatDetailScreenViewModel
import com.macaosoftware.nav3playground.moduleA.ui.view.ScreenAViewModel
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.GraphExtension
import dev.zacsweers.metro.Scope

@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
annotation class ModuleANodeScope

@GraphExtension(ModuleANodeScope::class)
interface ModuleANodeGraph {

    val screenAViewModel: ScreenAViewModel
    val chatDetailScreenViewModel: ChatDetailScreenViewModel

    @GraphExtension.Factory
    @ContributesTo(AppScope::class)
    interface Factory {
        fun createModuleANodeGraph(): ModuleANodeGraph
    }
}