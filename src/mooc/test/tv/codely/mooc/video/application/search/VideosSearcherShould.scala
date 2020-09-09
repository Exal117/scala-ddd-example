package tv.codely.mooc.video.application.search

import tv.codely.mooc.video.domain._
import tv.codely.mooc.video.infrastructure.repository.VideoRepositoryMock
import tv.codely.shared.infrastructure.unit.UnitTestCase

final class VideosSearcherShould extends UnitTestCase with VideoRepositoryMock {
  private val searcher = new VideosSearcher(repository)

  "search all existing videos" in {
    val existingVideo        = VideoMother.random
    val anotherExistingVideo = VideoMother.random
    val existingVideos       = Seq(existingVideo, anotherExistingVideo)

    repositoryShouldFind(existingVideos)

    searcher.all().futureValue shouldBe existingVideos
  }

  "search an existing video" in {
    val existingVideo = VideoMother.random

    repositoryShouldFindVideo(existingVideo)

    searcher.find(existingVideo.id).futureValue.get shouldBe existingVideo

  }
}
