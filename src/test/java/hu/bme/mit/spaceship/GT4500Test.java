package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryTorpedoStore;
  private TorpedoStore secondaryTorpedoStore;
  @BeforeEach
  public void init(){
    this.ship = new GT4500();
    primaryTorpedoStore = mock(TorpedoStore.class);
    secondaryTorpedoStore = mock(TorpedoStore.class);
    this.ship = new GT4500(primaryTorpedoStore, secondaryTorpedoStore);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
	  
	//stub method call
	when(primaryTorpedoStore.isEmpty()).thenReturn(false);
	when(primaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    
    // Assert
    assertEquals(true, result);
    // 
    verify(primaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
	  when(primaryTorpedoStore.fire(1)).thenReturn(true);
	  when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    
    
    // Assert
    assertEquals(true, result);
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void primaryTorpido_fire_again(){
    // Arrange
	  when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
	  when(primaryTorpedoStore.isEmpty()).thenReturn(false);
	  when(primaryTorpedoStore.fire(1)).thenReturn(true);
    // Act

	ship.fireTorpedo(FiringMode.SINGLE);
    //second time
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(true, result);
  
    verify(primaryTorpedoStore, times(2)).fire(1);
   
  }

  @Test
  public void secondaryTorpedo_fire_first(){
    // Arrange
	  when(primaryTorpedoStore.isEmpty()).thenReturn(true);
	  when(primaryTorpedoStore.fire(1)).thenReturn(false);
	  
	  when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
	  when(secondaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    
    // Assert
    assertEquals(true, result);
    verify(secondaryTorpedoStore, times(1)).fire(1);
   
  }
  @Test
  public void primaryTorpedo_fire_first(){
    // Arrange
	  when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
	  when(secondaryTorpedoStore.fire(1)).thenReturn(false);
	  
	  when(primaryTorpedoStore.isEmpty()).thenReturn(false);
	  when(primaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    
    // Assert
    assertEquals(true, result);
    verify(primaryTorpedoStore, times(1)).fire(1);
   
  }
  @Test
  public void both_Torpedo_empty(){
    // Arrange
	  when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
	  when(secondaryTorpedoStore.fire(1)).thenReturn(false);
	  
	  when(primaryTorpedoStore.isEmpty()).thenReturn(true);
	  when(primaryTorpedoStore.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    
    // Assert
    assertEquals(false, result);
    verify(primaryTorpedoStore, times(1)).isEmpty();
    verify(secondaryTorpedoStore, times(1)).isEmpty();
  }
  @Test
  public void secondaryTorpedo_fire_again(){
    // Arrange
	  when(primaryTorpedoStore.isEmpty()).thenReturn(true);
	  when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
	  when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    // Act

	ship.fireTorpedo(FiringMode.SINGLE);
    //second time
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(true, result);
  
    verify(secondaryTorpedoStore, times(2)).fire(1);
   
  }
  
}
