package interpreter;

import game.shellda.Element;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ICondition {

	public ICondition() {
	}

	boolean eval(Element e) {
		return true;
	} // à redéfinir dans chaque sous-classe
	
	public static class CanMove extends ICondition{
		Direction direction;
		
		public CanMove(Direction direction) {
			// TODO Auto-generated constructor stub
			this.direction=direction;
		}

		boolean eval(Element e){
			return e.canmove(this.direction);
			
		}
	}
	
	public static class CinqPas extends ICondition{
		int nbpas;
		public CinqPas(int nbpas) {
			this.nbpas=nbpas;			
		}
		boolean eval(Element e) {
			return e.cinqpas(this.nbpas);
		}
	}

	/*
	 * public class True extends Condition { True(){} boolean eval(Entity e) {
	 * return true; } }
	 * 
	 * public class Cell extends Condition { Direction direction ; Kind kind ;
	 * Distance distance ;
	 * 
	 * Cell(Direction direction, Kind kind, Distance distance){ this.direction =
	 * direction ; this.kind = kind ; this.distance = distance ; }
	 * 
	 * Cell(Direction direction, Kind kind){ this.direction = direction ; this.kind
	 * = kind ; this.distance = 1 ; }
	 * 
	 * boolean eval(Entity e) { return is_Kind(this.kind, this.direction,
	 * this.distance, e.position, e.map) ; } }
	 * 
	 * public class GotPower extends Condition { GotPower(){} boolean eval(Entity e)
	 * { return (e.power > 0) ; } }
	 */

}