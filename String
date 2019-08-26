
// Summary
// Objective: Run all String tools without using their functions.
// Input: String.
// Output: String.


//Libraries

#include<stdio.h>
#include<string.h>
#define TAM 20


//Functions
int funLen(char str[]);
void funPuts( char str[]);
void funUpr( char str[]);
int funCmp( char str[], char text[]);
void funCpy( char str[], char text[]);
void funCat( char str[], char text[]);
void funGets(char str[]);


// Main

int main(void) {

	// Variables.
	char str[TAM];
	int var;

	//Instructions.
	//gets
	//funGets(str);
	//strLen
	//var = funLen(str);
	//puts
	//funPuts(str);
	//strupr
	//funUpr(str);
	//strcmp
	//var = funCmp(str,"wow");
	//strcpy
	//funCpy(str," Something");
	//strcat
	//funCat(str," Something");

	return 0;
}

// Summary
// Objective: Create a function that replaces the "strlen".
// Input: Characters, texts.
//Get out.
int funLen(char str[]) {

	int i=0;

	while(str[i]!='\0') {
		i++;
	}
	return i;
}

// Summary
// Objective: Create a function that replaces the "Puts".
// Input: Characters, texts.
//Output: -
void funPuts( char str[]) {

	// Variables.

	int i=0, final;

	//Instructions.
	while(str[i]!='\0') {
		i++;
	}
	final=i;

	for(i=0; i<=final; i++) {
		if(i!=final)
			printf("%c",str[i]);
		else
			printf("%c \n",str[i]);
	}

}

// Summary
// Objective: Create a function that will replace the "strupr".
// Input: Characters, texts.
//Output: -
void funUpr( char str[]) {

	// Variables.
	int i=0;

	//Instructions.
	while(str[i]!='\0') {
		str[i]=toupper(str[i]);
		i++;
	}
	printf("%s \n",str);

}

// Summary
// Objective: Create a function that will replace the "strcmp".
// Input: Characters, texts.
//Output: -
int funCmp( char str[], char text[]) {

	// Variables.
	int i=0,j=0, flag=1;

	//Instructions.
	printf("%s",text);
	for (i=0; i<funLen(str); i++) {
		if (text[i] == str[i]) {
			j++;
		}
	}
	if (j==funLen(str)) {
		return flag=0;

	}
	return flag;

}


// Summary
// Objective: Create a function that replaces "strcpy".
// Input: Characters, texts.
//Output: -
void funCpy( char str[], char text[]) {

	// Variables.
	int i=0;

	//Instructions.
	while(text[i]!='\0') {
		str[i]=text[i];
		i++;
	}
	str[i] = '\0';
	printf("%s \n",str);
}

// Summary
// Objective: Create a function that replaces "strcat".
// Input: Characters, texts.
//Output: -
void funCat( char str[], char text[]) {

	// Variables.
	int i=0, final;

	//Instructions.
	while(str[i]!='\0') {
		i++;
	}
	final=i;
	i=0;
	while(text[i]!='\0') {
		str[final+i]=text[i];
		i++;
	}
	final+=i;
	str[final] = '\0';
	printf("%s \n",str);
}

// Summary
// Objective: Create a function that overrides "gets".
// Input: Characters, texts.
//Output: -
void funGets(char str[]) {

	// Variables.
	char text;
	int i=0;

	//Instructions.
	while( (text = getchar()) != '\n'   &&   text != EOF ) {
		str[i] = text;
		++i;
	}
	str[i] = '\0';
	fflush(stdin);
	system("PAUSE");
	system("CLS");

}
