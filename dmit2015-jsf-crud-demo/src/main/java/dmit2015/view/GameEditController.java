package dmit2015.view;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import dmit2015.model.Game;
import dmit2015.service.GameService;
import lombok.Getter;

@Named
@ViewScoped
public class GameEditController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject @ManagedProperty("#{param.editId}")
	private String editId;
	
	@Inject
	private GameService gameService;
	
	@Getter
	private Game existingGame;
	
	@PostConstruct	// After @Inject is completed
	public void init() {
		// If the page is not reloaded using a post-back
		if (!Faces.isPostback()) {
			UUID gameId = UUID.fromString(editId);
			Optional<Game> optionalGame = gameService.findById(gameId);
			if (optionalGame.isPresent()) {
				existingGame = optionalGame.get();
			} else {
				Messages.addGlobalFatal("A valid gameId is required.");
				
			}
		}
		
	}
	
	public String updateGame() {
		gameService.update(existingGame);
		Messages.addFlashGlobalInfo("Update game was successful.");
		return "manageGames?faces-redirect=true";	// faces-redirect=true forces the URL in the browser to be updated
	}
	
	public String deleteGame() {
		gameService.remove(existingGame.getGameId());
		Messages.addFlashGlobalInfo("Delete game was successful.");
		return "manageGames";		// the URL in the browser is not updated
	}
	

}
