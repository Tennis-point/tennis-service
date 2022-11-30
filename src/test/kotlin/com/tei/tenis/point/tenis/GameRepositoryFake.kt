package com.tei.tenis.point.tenis

import com.tei.tenis.point.tenis.app.GameRepository
import com.tei.tenis.point.tenis.domain.game.Game
import com.tei.tenis.point.tenis.infrastracture.db.GameEntity
import com.tei.tenis.point.tenis.infrastracture.db.GameRepositorySql
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Function

class GameRepositoryFake : GameRepositorySql {

    var map: HashMap<String, GameEntity> = HashMap()

    override fun <S : GameEntity?> save(entity: S): S {
        if (entity != null) {
            map[entity.gameId] = entity
        };
        return map[entity!!.gameId] as S
    }

    override fun <S : GameEntity?> saveAll(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun findById(id: String): Optional<GameEntity> {
        return Optional.of(map[id]!!);
    }

    override fun existsById(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableList<GameEntity> {
        TODO("Not yet implemented")
    }

    override fun findAll(sort: Sort): MutableList<GameEntity> {
        TODO("Not yet implemented")
    }

    override fun <S : GameEntity?> findAll(example: Example<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun <S : GameEntity?> findAll(example: Example<S>, sort: Sort): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun findAll(pageable: Pageable): Page<GameEntity> {
        TODO("Not yet implemented")
    }

    override fun <S : GameEntity?> findAll(example: Example<S>, pageable: Pageable): Page<S> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<String>): MutableList<GameEntity> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun <S : GameEntity?> count(example: Example<S>): Long {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: String) {
        TODO("Not yet implemented")
    }

    override fun delete(entity: GameEntity) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<String>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<GameEntity>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun <S : GameEntity?> findOne(example: Example<S>): Optional<S> {
        TODO("Not yet implemented")
    }

    override fun <S : GameEntity?> exists(example: Example<S>): Boolean {
        TODO("Not yet implemented")
    }

    override fun <S : GameEntity?, R : Any?> findBy(example: Example<S>, queryFunction: Function<FluentQuery.FetchableFluentQuery<S>, R>): R {
        TODO("Not yet implemented")
    }

    override fun flush() {
        TODO("Not yet implemented")
    }

    override fun <S : GameEntity?> saveAndFlush(entity: S): S {
        TODO("Not yet implemented")
    }

    override fun <S : GameEntity?> saveAllAndFlush(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun deleteAllInBatch(entities: MutableIterable<GameEntity>) {
        TODO("Not yet implemented")
    }

    override fun deleteAllInBatch() {
        TODO("Not yet implemented")
    }

    override fun deleteAllByIdInBatch(ids: MutableIterable<String>) {
        TODO("Not yet implemented")
    }

    override fun getOne(id: String): GameEntity {
        TODO("Not yet implemented")
    }

    override fun getById(id: String): GameEntity {
        TODO("Not yet implemented")
    }

    override fun getReferenceById(id: String): GameEntity {
        TODO("Not yet implemented")
    }
}
