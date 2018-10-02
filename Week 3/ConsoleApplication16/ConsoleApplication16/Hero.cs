using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApplication16
{
    public class Hero
    {
        private static HeroState[] allStates = {
            new NormalState(),
            new InjuredState(),
            new BerserkState()
        };
        protected HeroState state = new NormalState();
        private int hp=100;

        public int HP
        {
            get
            {
                return hp;
            }
            set
            {
                hp = value;
                ChangeStateIfNecessary(hp);
            }
        }

        private HeroState currentState;// = allStates[0];

        public Hero()
        {
            currentState = Hero.allStates[0];
        }
        private void ChangeStateIfNecessary(int hp)
        {
            /*            if (hp >= 80)
                            state = new NormalState();
                        else if (hp >= 40)
                            state = new InjuredState();
                        else
                            state = new BerserkState();*/

            if (hp >= 80)
                currentState = Hero.allStates[0];
            else if (hp >= 40)
                currentState = Hero.allStates[1];
            else
                currentState = Hero.allStates[2];

        }


    }
}