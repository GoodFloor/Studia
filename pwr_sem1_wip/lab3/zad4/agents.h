struct agent
{
    int posX;
    int posY;
};

struct agent newagent(int X, int Y);

void north(struct agent *a);
void south(struct agent *a);
void east(struct agent *a);
void west(struct agent *a);

double distance(struct agent a1, struct agent a2);
