package io.zenandroid.onlinego.game

import android.graphics.Point
import android.support.annotation.ColorRes
import io.reactivex.Observable
import io.zenandroid.onlinego.R
import io.zenandroid.onlinego.model.Position
import io.zenandroid.onlinego.model.StoneType
import io.zenandroid.onlinego.model.local.Game
import io.zenandroid.onlinego.model.local.Message
import io.zenandroid.onlinego.model.local.Player
import io.zenandroid.onlinego.statuschips.Chip

/**
 * Created by alex on 10/11/2017.
 */
interface GameContract {

    interface View {
        var boardSize: Int
        var whitePlayer: Player?
        var blackPlayer: Player?
        var position: Position?
        val cellSelection: Observable<Point>
        val cellHotTrack: Observable<Point>
        var interactive: Boolean
        var passButtonEnabled: Boolean
        fun showCandidateMove(point: Point?, nextToMove: StoneType? = null)
        var previousButtonEnabled: Boolean
        var nextButtonEnabled: Boolean
        var title: String?
        var nextButtonVisible: Boolean
        var analyzeButtonVisible: Boolean
        var analysisDisabledButtonVisible: Boolean
        var previousButtonVisible: Boolean
        var passButtonVisible: Boolean
        var resignButtonVisible: Boolean
        var confirmButtonVisible: Boolean
        var discardButtonVisible: Boolean
        var autoButtonVisible: Boolean
        var bottomBarVisible: Boolean
        var menuButtonVisible: Boolean
        var showLastMove: Boolean
        var showTerritory: Boolean
        var fadeOutRemovedStones: Boolean
        var whiteTimer: GamePresenter.TimerDetails?
        var blackTimer: GamePresenter.TimerDetails?
        fun showError(t: Throwable)
        var chatMyId: Long?
        var whiteScore: Float
        var blackScore: Float

        fun setLoading(loading: Boolean)
        fun showFinishedDialog()
        fun showYouWinDialog()
        fun showYouLoseDialog()
        fun setWhitePlayerStatus(text: String?, @ColorRes color: Int = R.color.colorAccent)
        fun setBlackPlayerStatus(text: String?, @ColorRes color: Int = R.color.colorAccent)
        fun setChips(chips: List<Chip>)
        fun showInfoDialog(title: String, contents: String)
        fun setBlackPlayerPassed(passed: Boolean)
        fun setWhitePlayerPassed(passed: Boolean)
        fun setMessageList(messages: List<Message>)
        fun showChat()
        fun setNewMessagesCount(count: Int)
        fun showUndoPrompt()
        fun showMenu(options: List<MenuItem>)
        fun showPassConfirmation()
        fun showResignConfirmation()
        fun navigateTo(url: String)
        fun showGameInfoDialog(game: Game)
        fun showAbortGameConfirmation()
        fun showAbortedDialog()
        fun showKoDialog()
    }

    interface Presenter {
        fun subscribe()
        fun unsubscribe()
        fun onResignConfirmed()
        fun onPassConfirmed()

        fun onPreviousButtonPressed()
        fun onNextButtonPressed()
        fun onDiscardButtonPressed()
        fun onConfirmButtonPressed()
        fun onAutoButtonPressed()
        fun onAnalyzeButtonClicked()
        fun onAnalysisDisabledButtonClicked()
        fun onChatClicked()
        fun onNewMessage(message: String)
        fun onUndoAccepted()
        fun onUndoRejected()
        fun onMenuButtonPressed()
        fun onMenuItemSelected(item: MenuItem)
        fun onPassClicked()
        fun onResignClicked()
        fun onAbortGameConfirmed()
    }

    sealed class MenuItem {
        object GAME_INFO: MenuItem()
        object PASS: MenuItem()
        object RESIGN: MenuItem()
        object ESTIMATE_SCORE: MenuItem()
        object ANALYZE: MenuItem()
        object DOWNLOAD_SGF: MenuItem()
        object ACCEPT_UNDO: MenuItem()
        object REQUEST_UNDO: MenuItem()
        object ABORT_GAME: MenuItem()
    }
}