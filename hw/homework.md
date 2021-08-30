Kristiine, a regular Estonian woman, is a multifaceted person. On regular weekdays she spends most of her time at boring consultancy company X working as a Java Engineer. 

Last week, during a surely very interesting project, she had a wakeup call after attempting to implement  the `AbstractSingletonProxyFactoryBean` class.

That wakeup call made her realise that she ought to focus on what truly brings her joy, the Dungeons & Dragons friday evenings she has with her mates.

While playing D&D with her friends she noticed that every time someone died during the session ~~as in, in the roleplaying, not in real life :D~~ or whenever a new adventure was started, they'd waste a couple of hours trying to create new characters.

Seeing that creating characters was a chore, she thought that it'd be a good idea to routinely generate simple permutations of basic characters.

After thinking it through, she came down to this spec:

- The generated characters should be simple, and of low level(up to including 3)
- The process should happen once a week, before friday evening's sessions
- The characters should be added to a single table, on the airflow table at the given postgres database, with the following format(everything is varchar).

1. name*
2. attributes*
3. race*
4. languages*
5. class*
6. profficiency_choices*
7. level*
8. spells*

* name - You can generate **any**
names. Preferably you should just consume from a fake/random name generator apis out there, or even just name it "Wizard-1", "Wizard2" and so on. In order to make your life easier, python package `faker` is included.
* Attributes - varchar containing:
* * (strength(integer from 6-18)
, dexterity(integer from 2-18)
, constitution(integer from 2-18)
, intelligence(integer from 2-18)
, wisdom(integer from 2-18)
, charisma(integer from 2-18)) for instance: "[16, 12, 10, 6, 10, 14]"
* Spells - the maximum learnable spell level is 2, so, when picking your random spells make sure to check their level. Also, do not pick spells which are not know by those in your class. Some classes, like fighter, don't cast spells at all. In order to keep things simple, the number of spells to be selected should be of level + 3
* Proficiencies and languages - make sure to use the INDEX not the name. remember, it's always easier to add anything to a database if it's lower case, spaceless and comma-less. 
* Languages: any.

In order to get it done she asked you, a bright student taking the Data Engineering class, to define the dataflow, and, as mentioned previously that, once a week, in her database, there have to be 5 generated characters ready for her less-fortunate session peers to use.

Tips and minimal expectations:

read the docs of the API that we will use. It's very simple and didatic, no objects are deeply nested: https://www.dnd5eapi.co/docs/

You should not need to use anything other than what has been given in class, airflow-wise.

You are **not** allowed to modify the docker image in any way. Even adding new packages. Work with what has been given and nothing else. Calling external **public** APIs is ok, as long as they are safe and not require any sort of authentication.

Try to make it so that each node does the minimum amount of work as it is reasonable i.e don't delegate more than one task per node.

The minimum number of nodes of each type that you are required to have are:

Dummy operators: 2 The end node must be a dummy one with `none_failed` as a `trigger rule.`
Python operators: 4
Branch Python operators: 1
Postgres operator: 1