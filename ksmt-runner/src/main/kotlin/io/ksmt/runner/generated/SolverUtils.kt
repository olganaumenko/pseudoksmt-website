/**
 * Do not edit.
 * Generated by SolverUtilsGenerator.
 * */

package io.ksmt.runner.generated

import io.ksmt.KContext
import io.ksmt.runner.generated.models.SolverType
import io.ksmt.solver.KSolver
import io.ksmt.solver.KSolverConfiguration
import io.ksmt.solver.KSolverUniversalConfigurationBuilder
import kotlin.reflect.KClass

typealias ConfigurationBuilder<C> = (KSolverUniversalConfigurationBuilder) -> C

internal fun createSolverConstructor(solverQualifiedName: String): (KContext) -> KSolver<*> {
    val cls = Class.forName(solverQualifiedName)
    val ctor = cls.getConstructor(KContext::class.java)
    return { ctx: KContext -> ctor.newInstance(ctx) as KSolver<*> }
}

internal fun createConfigConstructor(
    configQualifiedName: String
): (KSolverUniversalConfigurationBuilder) -> KSolverConfiguration {
    val cls = Class.forName(configQualifiedName)
    val ctor = cls.getConstructor(KSolverUniversalConfigurationBuilder::class.java)
    return { builder: KSolverUniversalConfigurationBuilder -> ctor.newInstance(builder) as KSolverConfiguration }
}

private val solverConstructorZ3: (KContext) -> KSolver<*> by lazy {
    createSolverConstructor("io.ksmt.solver.z3.KZ3Solver")
}

private val configConstructorZ3: (KSolverUniversalConfigurationBuilder) -> KSolverConfiguration by lazy {
    createConfigConstructor("io.ksmt.solver.z3.KZ3SolverUniversalConfiguration")
}

private val solverConstructorBitwuzla: (KContext) -> KSolver<*> by lazy {
    createSolverConstructor("io.ksmt.solver.bitwuzla.KBitwuzlaSolver")
}

private val configConstructorBitwuzla: (KSolverUniversalConfigurationBuilder) -> KSolverConfiguration by lazy {
    createConfigConstructor("io.ksmt.solver.bitwuzla.KBitwuzlaSolverUniversalConfiguration")
}

private val solverConstructorYices: (KContext) -> KSolver<*> by lazy {
    createSolverConstructor("io.ksmt.solver.yices.KYicesSolver")
}

private val configConstructorYices: (KSolverUniversalConfigurationBuilder) -> KSolverConfiguration by lazy {
    createConfigConstructor("io.ksmt.solver.yices.KYicesSolverUniversalConfiguration")
}

private val solverConstructorCvc5: (KContext) -> KSolver<*> by lazy {
    createSolverConstructor("io.ksmt.solver.cvc5.KCvc5Solver")
}

private val configConstructorCvc5: (KSolverUniversalConfigurationBuilder) -> KSolverConfiguration by lazy {
    createConfigConstructor("io.ksmt.solver.cvc5.KCvc5SolverUniversalConfiguration")
}

private val solverTypes = mapOf(
    "io.ksmt.solver.z3.KZ3Solver" to SolverType.Z3,
    "io.ksmt.solver.bitwuzla.KBitwuzlaSolver" to SolverType.Bitwuzla,
    "io.ksmt.solver.yices.KYicesSolver" to SolverType.Yices,
    "io.ksmt.solver.cvc5.KCvc5Solver" to SolverType.Cvc5,
)

val KClass<out KSolver<*>>.solverType: SolverType
    get() = solverTypes[qualifiedName] ?: SolverType.Custom

fun SolverType.createInstance(ctx: KContext): KSolver<*> = when (this) {
    SolverType.Z3 -> solverConstructorZ3(ctx)
    SolverType.Bitwuzla -> solverConstructorBitwuzla(ctx)
    SolverType.Yices -> solverConstructorYices(ctx)
    SolverType.Cvc5 -> solverConstructorCvc5(ctx)
    SolverType.Custom -> error("User defined solvers should not be created with this builder")
}

@Suppress("UNCHECKED_CAST")
fun <C : KSolverConfiguration> SolverType.createConfigurationBuilder(): ConfigurationBuilder<C> = when (this) {
    SolverType.Z3 -> { builder -> configConstructorZ3(builder) as C }
    SolverType.Bitwuzla -> { builder -> configConstructorBitwuzla(builder) as C }
    SolverType.Yices -> { builder -> configConstructorYices(builder) as C }
    SolverType.Cvc5 -> { builder -> configConstructorCvc5(builder) as C }
    SolverType.Custom -> error("User defined solver config builders should not be created with this builder")
}

